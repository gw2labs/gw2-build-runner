package de.ralfhergert.gw2.api.v2;

import de.ralfhergert.generic.throttling.PerformQueuedRequestRunnable;
import de.ralfhergert.generic.throttling.QueuedRequestPerformer;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class ThrottledRequest {

    private static final QueuedRequestPerformer performer = new QueuedRequestPerformer(new PerformQueuedRequestRunnable(HttpClient.newHttpClient(), Duration.ofMillis(100)));

    protected CompletableFuture<HttpResponse<String>> execute(HttpRequest request) {
        return performer.perform(request);
    }
}
