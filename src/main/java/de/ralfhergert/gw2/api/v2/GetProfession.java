package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetProfession {

    private final String profession;

    private final HttpRequest request;

    private final ObjectReader reader = new ObjectMapper().readerFor(Gw2Profession.class);

    public GetProfession(String profession) {
        this.profession = profession;
        request = HttpRequest
            .newBuilder(URI.create("https://api.guildwars2.com/v2/professions/" + profession + "?lang=en"))
            .GET()
            .build();
    }

    public Gw2Profession get() {
        String body = HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        try {
            return reader.readValue(body);
        } catch (JsonProcessingException e) {
            throw new Error("Could not fetch profession '" + profession + "'", e);
        }
    }
}
