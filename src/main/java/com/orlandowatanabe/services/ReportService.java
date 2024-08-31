package com.orlandowatanabe.services;

import com.orlandowatanabe.clients.ImdbApiClient;
import com.orlandowatanabe.clients.MarvelApiClient;
import com.orlandowatanabe.interfaces.Content;
import com.orlandowatanabe.models.Movie;
import com.orlandowatanabe.models.Series;
import com.orlandowatanabe.parsers.ImdbMovieJsonParser;
import com.orlandowatanabe.parsers.MarvelComicJsonParser;
import com.orlandowatanabe.presentation.HTMLGenerator;
import com.orlandowatanabe.utils.HtmlFileUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Collection;


public class ReportService {
    private final ImdbApiClient imdbApiClient;
    private final MarvelApiClient marvelApiClient;
    private final ImdbMovieJsonParser imdbMovieParser;
    private final MarvelComicJsonParser marvelComicParser;

    public ReportService(ImdbApiClient imdbApiClient, MarvelApiClient marvelApiClient,
                         ImdbMovieJsonParser imdbMovieParser, MarvelComicJsonParser marvelComicParser) {
        this.imdbApiClient = imdbApiClient;
        this.marvelApiClient = marvelApiClient;
        this.imdbMovieParser = imdbMovieParser;
        this.marvelComicParser = marvelComicParser;
    }

    public void generateReport(String reportPath) throws IOException, InterruptedException {
        try {
            String imdbJsonResponse = imdbApiClient.getBody();
            System.out.println("MovieJson: " + imdbJsonResponse);
            List<Movie> moviesList = imdbMovieParser.parse(imdbJsonResponse);

            for (Movie movie : moviesList) {
                System.out.println(movie.getTitle() + " - " + movie.getYear());
            }
            String marvelJsonResponse = marvelApiClient.getBody();
            System.out.println("MarvelJson: " + marvelJsonResponse);
            List<Series> marvelList = marvelComicParser.parse(marvelJsonResponse);

            List<Content> mixedList = Stream.of(moviesList, marvelList)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            System.out.println("Mixed List");

            mixedList.sort(Comparator.comparing(
                    Content::title,
                    Comparator.nullsLast(Comparator.naturalOrder())
            ));

            System.out.println("Mix Sort");

            writeReportToHtml(mixedList, reportPath, () -> {
                try {
                    return new HTMLGenerator<>(createPrintWriter(reportPath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException io) {
            System.err.println("Erro na operação de entrada e saida: " + io.getMessage());
            handleException(io);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleException(Exception e) {
        System.err.println("Erro: " + e.getMessage());
        throw new RuntimeException(e);
    }

    private PrintWriter createPrintWriter(String filePath) throws IOException {
        return new PrintWriter(new FileWriter(filePath));
    }

    private void writeReportToHtml(List<Content> mixedList, String reportPath, Supplier<HTMLGenerator<Movie>> generatorSupplier) throws IOException {
        HtmlFileUtils.writeToHtml(mixedList, reportPath, generatorSupplier);
    }
}
