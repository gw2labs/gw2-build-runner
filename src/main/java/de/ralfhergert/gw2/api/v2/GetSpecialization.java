package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetSpecialization {

    private final int id;

    private final HttpRequest request;

    private final ObjectReader reader = new ObjectMapper().readerFor(Gw2Specialization.class);

    public GetSpecialization(int id) {
        this.id = id;
        request = HttpRequest
            .newBuilder(URI.create("https://api.guildwars2.com/v2/specializations/" + id + "?lang=en"))
            .GET()
            .build();
    }

    public Gw2Specialization get() {
        String body = HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        try {
            return reader.readValue(body);
        } catch (JsonProcessingException e) {
            throw new Error("Could not fetch specialization with id '" + id + "'", e);
        }
    }
}
