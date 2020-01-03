package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GetAllProfessions {

    private final HttpRequest request = HttpRequest
        .newBuilder(URI.create("https://api.guildwars2.com/v2/professions"))
        .GET()
        .build();

    private final ObjectReader reader = new ObjectMapper().readerFor(new TypeReference<List<String>>(){});

    public List<String> get() {
        String body = HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        try {
            return reader.readValue(body);
        } catch (JsonProcessingException e) {
            throw new Error("Could not fetch all professions", e);
        }
    }
}
