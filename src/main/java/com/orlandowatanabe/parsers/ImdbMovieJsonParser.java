package com.orlandowatanabe.parsers;

import com.orlandowatanabe.models.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImdbMovieJsonParser {

    private String json;

    public ImdbMovieJsonParser(String json) {
        this.json = json;
    }

    public List<Movie> parse() {
        List<Movie> movies = new ArrayList<>();

        // Expressões regulares para capturar os campos Title, Year, imdbID, Type e Poster
        Pattern titlePattern = Pattern.compile("\"Title\":\"(.*?)\"");
        Pattern yearPattern = Pattern.compile("\"Year\":\"(.*?)\"");
        Pattern imdbIDPattern = Pattern.compile("\"imdbID\":\"(.*?)\"");
        Pattern typePattern = Pattern.compile("\"Type\":\"(.*?)\"");
        Pattern posterPattern = Pattern.compile("\"Poster\":\"(.*?)\"");

        Matcher titleMatcher = titlePattern.matcher(json);
        Matcher yearMatcher = yearPattern.matcher(json);
        Matcher imdbIDMatcher = imdbIDPattern.matcher(json);
        Matcher typeMatcher = typePattern.matcher(json);
        Matcher posterMatcher = posterPattern.matcher(json);

        while (titleMatcher.find() && yearMatcher.find() && imdbIDMatcher.find() && typeMatcher.find() && posterMatcher.find()) {
            String title = titleMatcher.group(1);
            String year = yearMatcher.group(1);
            String imdbID = imdbIDMatcher.group(1);
            String type = typeMatcher.group(1);
            String urlImage = posterMatcher.group(1);

            Movie movie = new Movie(title, year, imdbID, type, urlImage);
            movies.add(movie);
        }

        return movies;
    }
}