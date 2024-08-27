package com.orlandowatanabe;

import java.util.List;

import com.orlandowatanabe.clients.ImdbApiClient;
import com.orlandowatanabe.models.Movie;
import com.orlandowatanabe.parsers.ImdbMovieJsonParser;
import com.orlandowatanabe.utils.MovieUtils;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");
        String movieReportPath = dotenv.get("MOVIE_REPORT");


        try {
            // Chamada da API encapsulada
            ImdbApiClient apiClient = new ImdbApiClient(apiKey);
            String jsonResponse = apiClient.getBody();

            // Parseamento do JSON encapsulado
            ImdbMovieJsonParser jsonParser = new ImdbMovieJsonParser(jsonResponse);
            List<Movie> movies = jsonParser.parse();

            // Escrita do HTML usando MovieUtils
            MovieUtils.writeMoviesToHtml(movies, movieReportPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
