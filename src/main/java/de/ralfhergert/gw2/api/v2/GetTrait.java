package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GetTrait extends ThrottledRequest {

    private final int traitId;

    private final HttpRequest request;

    private final ObjectReader reader = new ObjectMapper().readerFor(Gw2Trait.class);

    public GetTrait(int traitId) {
        this.traitId = traitId;
        request = HttpRequest
            .newBuilder(URI.create("https://api.guildwars2.com/v2/traits/" + traitId + "?lang=en"))
            .GET()
            .build();
    }

    public Gw2Trait get() {
        try {
            return reader.readValue(execute(request).get(30, TimeUnit.SECONDS).body());
        } catch (JsonProcessingException | InterruptedException | ExecutionException | TimeoutException e) {
            throw new Error("Could not fetch trait '" + traitId + "'", e);
        }
    }
}
