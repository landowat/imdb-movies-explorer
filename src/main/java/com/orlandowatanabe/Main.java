package com.orlandowatanabe;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String apiKey = dotenv.get("API_KEY");
        String url = "http://www.omdbapi.com/?apikey=" + apiKey + "&s=Avengers";

        System.out.println("API Key: " + apiKey);

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            String bodyString = response.body();

            // Retirando a lista de filmes do restante da string body
            Pattern pattern = Pattern.compile("\\[(.*?)]");
            Matcher matcher = pattern.matcher(bodyString);

            String moviesList = "";

            while (matcher.find()) {
                moviesList = matcher.group(1);
                System.out.println(moviesList);
            }

            // Expressões regulares para capturar os campos Title, Year. imdbID,Type e Poster
            Pattern titlePattern = Pattern.compile("\"Title\":\"(.*?)\"");
            Pattern yearPattern = Pattern.compile("\"Year\":\"(.*?)\"");
            Pattern imdbIDPattern = Pattern.compile("\"imdbID\":\"(.*?)\"");
            Pattern typePattern = Pattern.compile("\"Type\":\"(.*?)\"");
            Pattern posterPattern = Pattern.compile("\"Poster\":\"(.*?)\"");

            List<String> titles = new ArrayList<>();
            List<String> years = new ArrayList<>();
            List<String> imdbID = new ArrayList<>();
            List<String> type = new ArrayList<>();
            List<String> posters = new ArrayList<>();

            Matcher titleMatcher = titlePattern.matcher(moviesList);
            Matcher yearMatcher = yearPattern.matcher(moviesList);
            Matcher imdbIDMatcher = imdbIDPattern.matcher(moviesList);
            Matcher typeMatcher = typePattern.matcher(moviesList);
            Matcher posterMatcher = posterPattern.matcher(moviesList);

            while (titleMatcher.find()) {
                titles.add(titleMatcher.group(1));
            }
            while (yearMatcher.find()) {
                years.add(yearMatcher.group(1));
            }
            while (imdbIDMatcher.find()) {
                imdbID.add(imdbIDMatcher.group(1));
            }
            while (typeMatcher.find()) {
                type.add(typeMatcher.group(1));
            }
            while (posterMatcher.find()) {
                posters.add(posterMatcher.group(1));
            }

            // Imprimindo os resultados
            System.out.println("Titles: " + titles);
            System.out.println("Years: " + years);
            System.out.println("IMDbID: " + imdbID);
            System.out.println("Type: " + type);
            System.out.println("Posters: " + posters);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
