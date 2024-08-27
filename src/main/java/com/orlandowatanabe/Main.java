package com.orlandowatanabe;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.orlandowatanabe.models.Movie;
import com.orlandowatanabe.utils.MovieUtils;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");
        String movieReportPath = dotenv.get("MOVIE_REPORT");
        String url = "http://www.omdbapi.com/?apikey=" + apiKey + "&s=Avengers";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String bodyString = response.body();

            // Retirando a lista de filmes do restante da string body
            Pattern pattern = Pattern.compile("\\[(.*?)]");
            Matcher matcher = pattern.matcher(bodyString);

            String moviesList = "";
            while (matcher.find()) {
                moviesList = matcher.group(1);
            }

            // Parsing dos filmes usando MovieUtils
            List<Movie> movies = MovieUtils.parseMovies(moviesList);

            // Escrita do HTML usando MovieUtils
            MovieUtils.writeMoviesToHtml(movies, movieReportPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
