package com.orlandowatanabe.clients;

import com.orlandowatanabe.interfaces.APIClient;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient implements APIClient {

    private String apiKey;

    public ImdbApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getBody() throws Exception {
        String url = "http://www.omdbapi.com/?apikey=" + apiKey + "&s=Avengers";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Body: " + response.body());

        return response.body();
    }
}
