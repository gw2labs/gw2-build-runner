package de.ralfhergert.generic.throttling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * This performer ensures that the given {@link PerformQueuedRequestRunnable} has
 * a thread to run in.
 */
public class QueuedRequestPerformer {

    private static final Logger LOG = LoggerFactory.getLogger(QueuedRequestPerformer.class);

    private final PerformQueuedRequestRunnable runnable;

    private Thread thread;

    public QueuedRequestPerformer(PerformQueuedRequestRunnable runnable) {
        this.runnable = runnable;
    }

    public CompletableFuture<HttpResponse<String>> perform(HttpRequest request) {
        LOG.debug("appending request {}", request);
        final CompletableFuture<HttpResponse<String>> future = runnable.append(request);
        ensureThreadIsRunning();
        return future;
    }

    private void ensureThreadIsRunning() {
        if (thread != null && !thread.isAlive()) {
            LOG.debug("thread {} is no longer alive: waiting to die", thread);
            try {
                thread.join();
            } catch (InterruptedException e) { /* ignore */ }
        }
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(runnable);
            LOG.debug("created new thread {} for running queued request", thread);
            thread.start();
        }
    }
}
