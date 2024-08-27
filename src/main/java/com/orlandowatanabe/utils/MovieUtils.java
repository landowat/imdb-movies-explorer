package com.orlandowatanabe.utils;

import com.orlandowatanabe.models.Movie;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieUtils {

    public static List<Movie> parseMovies(String moviesList) {
        List<Movie> movies = new ArrayList<>();

        // Expressões regulares para capturar os campos Title, Year, imdbID, Type e Poster
        Pattern titlePattern = Pattern.compile("\"Title\":\"(.*?)\"");
        Pattern yearPattern = Pattern.compile("\"Year\":\"(.*?)\"");
        Pattern imdbIDPattern = Pattern.compile("\"imdbID\":\"(.*?)\"");
        Pattern typePattern = Pattern.compile("\"Type\":\"(.*?)\"");
        Pattern posterPattern = Pattern.compile("\"Poster\":\"(.*?)\"");

        Matcher titleMatcher = titlePattern.matcher(moviesList);
        Matcher yearMatcher = yearPattern.matcher(moviesList);
        Matcher imdbIDMatcher = imdbIDPattern.matcher(moviesList);
        Matcher typeMatcher = typePattern.matcher(moviesList);
        Matcher posterMatcher = posterPattern.matcher(moviesList);

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

    public static void writeMoviesToHtml(List<Movie> movies, String filePath) {
        File outputDirectory = new File(filePath).getParentFile();

        // Verificando se o diretório existe, caso contrário, criando-o
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        try (Writer writer = new FileWriter(filePath)) {
            // Instanciando a classe HTMLGenerator com o Writer
            HTMLGenerator htmlGenerator = new HTMLGenerator(writer);
            htmlGenerator.generate(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
