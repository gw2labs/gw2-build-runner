package de.ralfhergert.generic.throttling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This runnable allows to perform multiple HTTP-requests in a throttled manner.
 * Whenever one of the requests receives an HTTP-429-too-many-requests, the grace
 * period between the requests is increased an the affected request is retried.
 */
public class PerformQueuedRequestRunnable implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(PerformQueuedRequestRunnable.class);

    private final HttpClient httpClient;

    private final List<QueuedRequest> requestQueue = new ArrayList<>();

    private Duration gracePeriod;
    private LocalDateTime lastRequestPerformedTimestamp;

    public PerformQueuedRequestRunnable(HttpClient httpClient, Duration gracePeriod) {
        this.httpClient = httpClient;
        this.gracePeriod = gracePeriod;
    }

    public CompletableFuture<HttpResponse<String>> append(HttpRequest request) {
        final CompletableFuture<HttpResponse<String>> future = new CompletableFuture<>();
        requestQueue.add(new QueuedRequest(request, future));
        return future;
    }

    @Override
    public void run() {
        while (!requestQueue.isEmpty()) {
            QueuedRequest nextRequest = requestQueue.remove(0);
            if (lastRequestPerformedTimestamp == null
                || LocalDateTime.now().isAfter(lastRequestPerformedTimestamp.plus(gracePeriod))) {
                try {
                    HttpResponse<String> response = executeRequest(nextRequest);
                    nextRequest.getFuture().complete(response);
                } catch (TooManyRequestsException e) {
                    requestQueue.add(0, nextRequest); // put the request back into the queue
                } catch (IOException e) {
                    LOG.warn("IOException for request {}: retrying request after grace period", nextRequest, e);
                    requestQueue.add(0, nextRequest); // put the request back into the queue
                } catch (InterruptedException e) {
                    // ignore
                }
            }

            /*if (requestQueue.isEmpty()) {
                return;
            }*/

            try { // wait of the next execution
                final Duration timeToWait = Duration.between(LocalDateTime.now(), lastRequestPerformedTimestamp.plus(gracePeriod).plus(Duration.ofMillis(1)));
                if (timeToWait.isNegative() || timeToWait.isZero()) {
                    continue;
                }
                LOG.debug("sleeping for {}", timeToWait);
                Thread.sleep(timeToWait.toMillis());
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    private HttpResponse<String> executeRequest(QueuedRequest queuedRequest) throws TooManyRequestsException, IOException, InterruptedException {
        LOG.debug("performing request {}", queuedRequest.request);
        lastRequestPerformedTimestamp = LocalDateTime.now();
        HttpResponse<String> response = httpClient.send(queuedRequest.getRequest(), HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 429) {
            Duration newGracePeriod = Duration.ofNanos((long)(gracePeriod.toNanos() * 1.2));
            LOG.debug("received HTTP-429-response: prolonging grace period from {} to {}", gracePeriod, newGracePeriod);
            gracePeriod = newGracePeriod;
            throw new TooManyRequestsException();
        } else {
            return response;
        }
    }

    /**
     * Helper object to store an {@link HttpRequest} together with its response future.
     */
    private static class QueuedRequest {

        private final HttpRequest request;
        private final CompletableFuture<HttpResponse<String>> future;

        public QueuedRequest(HttpRequest request, CompletableFuture<HttpResponse<String>> future) {
            this.request = request;
            this.future = future;
        }

        public HttpRequest getRequest() {
            return request;
        }

        public CompletableFuture<HttpResponse<String>> getFuture() {
            return future;
        }
    }
}
