package com.orlandowatanabe.config;

import com.orlandowatanabe.clients.ImdbApiClient;
import com.orlandowatanabe.clients.MarvelApiClient;
import com.orlandowatanabe.parsers.ImdbMovieJsonParser;
import com.orlandowatanabe.parsers.MarvelComicJsonParser;
import com.orlandowatanabe.services.ReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AppConfigProperties appConfigProperties() {
        return new AppConfigProperties();
    }

    @Bean
    public ImdbApiClient imdbApiClient(AppConfigProperties properties) {
        return new ImdbApiClient(properties.getApiKey());
    }

    @Bean
    public MarvelApiClient marvelApiClient(AppConfigProperties properties) {
        return new MarvelApiClient(properties.getMarvelPublicKey(), properties.getMarvelPrivateKey());
    }

    @Bean
    public ImdbMovieJsonParser imdbMovieParser() {
        return new ImdbMovieJsonParser();
    }

    @Bean
    public MarvelComicJsonParser marvelComicParser() {
        return new MarvelComicJsonParser();
    }

    @Bean
    public ReportService reportService(ImdbApiClient imdbApiClient, MarvelApiClient marvelApiClient,
                                       ImdbMovieJsonParser imdbMovieParser, MarvelComicJsonParser marvelComicParser) {
        return new ReportService(imdbApiClient, marvelApiClient, imdbMovieParser, marvelComicParser);
    }
}
