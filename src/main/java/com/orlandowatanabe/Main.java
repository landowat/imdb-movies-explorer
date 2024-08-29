package com.orlandowatanabe;

import com.orlandowatanabe.config.AppConfigProperties;
import com.orlandowatanabe.services.ReportService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();

        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner run(ReportService reportService, AppConfigProperties properties) {
        return args -> {
            try {
                reportService.generateMovieReport(properties.getMovieReportPath());
                reportService.generateMarvelReport(properties.getMarvelReportPath());
            } catch (IOException io) {
                System.err.println("Erro na operação de entrada e saída: " + io.getMessage());
            } catch (InterruptedException ie) {
                System.err.println("Thread foi interrompida: " + ie.getMessage());
            } catch (Exception e) {
                System.err.println("Erro desconhecido: " + e.getMessage());
            }
        };
    }
}
