package com.orlandowatanabe.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfigProperties {
    @Value("${API_KEY}")
    private String apiKey;

    @Value("${MOVIE_REPORT}")
    private String movieReportPath;

    @Value("${MARVEL_PUBLIC_KEY}")
    private String marvelPublicKey;

    @Value("${MARVEL_PRIVATE_KEY}")
    private String marvelPrivateKey;

    @Value("${MARVEL_REPORT}")
    private String marvelReportPath;

    public String getApiKey() {
        return apiKey;
    }

    public String getMovieReportPath() {
        return movieReportPath;
    }

    public String getMarvelPublicKey() {
        return marvelPublicKey;
    }

    public String getMarvelPrivateKey() {
        return marvelPrivateKey;
    }

    public String getMarvelReportPath() {
        return marvelReportPath;
    }
}
