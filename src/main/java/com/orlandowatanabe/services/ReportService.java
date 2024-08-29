package com.orlandowatanabe.services;

import com.orlandowatanabe.clients.ImdbApiClient;
import com.orlandowatanabe.clients.MarvelApiClient;
import com.orlandowatanabe.interfaces.Content;
import com.orlandowatanabe.models.Movie;
import com.orlandowatanabe.parsers.ImdbMovieJsonParser;
import com.orlandowatanabe.parsers.MarvelComicJsonParser;
import com.orlandowatanabe.presentation.HTMLGenerator;
import com.orlandowatanabe.utils.HtmlFileUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Supplier;

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

    public void generateMovieReport(String reportPath) throws IOException, InterruptedException {
        try {
            String jsonResponse = imdbApiClient.getBody();
            List<Movie> movies = imdbMovieParser.parse(jsonResponse);
            writeReportToHtml(movies, reportPath, () -> {
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

    public void generateMarvelReport(String reportPath) throws IOException, InterruptedException {
        try {
            String marvelJsonResponse = marvelApiClient.getBody();
            List<? extends Content> marvelList = marvelComicParser.parse(marvelJsonResponse);
            HtmlFileUtils.writeToHtml(marvelList, reportPath, () -> {
                try {
                    return new HTMLGenerator<>(createPrintWriter(reportPath));
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao criar o FileWriter", e);
                }
            });
        } catch (IOException io) {
            System.err.println("Erro na operação de entrada e saida: " + io.getMessage());
            handleException(io);
        } catch (InterruptedException ie) {
            System.err.println("Thread foi interropida: " + ie.getMessage());
            handleException(ie);
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

    private void writeReportToHtml(List<Movie> movies, String reportPath, Supplier<HTMLGenerator<Movie>> generatorSupplier) throws IOException {
        HtmlFileUtils.writeToHtml(movies, reportPath, generatorSupplier);
    }
}
