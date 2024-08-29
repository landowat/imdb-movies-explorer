package com.orlandowatanabe.clients;

import com.orlandowatanabe.interfaces.APIClient;
import com.orlandowatanabe.utils.MD5HashGenerator;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MarvelApiClient implements APIClient {

    private String publicKey;
    private String privateKey;

    public MarvelApiClient(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public String getBody() throws Exception {
        long timestamp = System.currentTimeMillis();
        String hash = MD5HashGenerator.generate(timestamp + privateKey + publicKey);

        String url = "https://gateway.marvel.com/v1/public/comics?ts=" + timestamp +
                "&apikey=" + publicKey + "&hash=" + hash;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
