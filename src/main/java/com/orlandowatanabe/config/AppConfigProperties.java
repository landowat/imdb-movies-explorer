package com.orlandowatanabe.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfigProperties {
    @Getter
    @Value("${API_KEY}")
    private String apiKey;

    @Getter
    @Value("${MARVEL_PUBLIC_KEY}")
    private String marvelPublicKey;

    @Getter
    @Value("${MARVEL_PRIVATE_KEY}")
    private String marvelPrivateKey;

    @Value("${CONTENT_REPORT}")
    private String contentReport;


    public String getReportPath() {
        return contentReport;
    }
}
