package com.orlandowatanabe.presentation;

import com.orlandowatanabe.models.Movie;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public class HTMLGenerator {
    private final PrintWriter writer;

    public HTMLGenerator(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void generate(List<Movie> movies) {
        // Define o HTML do cabeçalho
        String head = """
        <head>
            <meta charset="utf-8">
            <title>Imdb Movies Explorer</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <style>
                body {
                    background-color: #282a36; /* Cor de fundo tema dracula */
                    color: #f8f8f2; /* Cor do texto padrão do tema Dracula */
                }
        
                .card-img-top {
                    height: 550px !important;
                    object-fit: cover !important; /* Garante que a imagem se ajuste ao container sem distorção */
                }
        
                .card-body {
                    background-color: #44475a !important; /* Define uma cor de fundo suave para o corpo do card */
                }
        
                .card-title {
                    color: #bd93f9 !important; /* Cor do título do card */
                }
        
                .card-text {
                    color: #f8f8f2 !important; /* Cor do texto do card */
                }
            </style>
        </head>
        """;

        // Define o HTML do corpo
        String bodyStart = """
        <body>
            <div class="container">
                <h1 class="my-4 text-center">Lista de Filmes</h1>
                <div class="row">
        """;

        // Adiciona a lista de filmes ao corpo
        StringBuilder moviesHtml = new StringBuilder();
        for (Movie movie : movies) {
            moviesHtml.append("<div class=\"col-md-4 mb-4\">\n")
                    .append("    <div class=\"card\">\n")
                    .append("        <img src=\"").append(movie.urlImage()).append("\" class=\"card-img-top\" alt=\"Poster do Filme\">\n")
                    .append("        <div class=\"card-body\">\n")
                    .append("            <h5 class=\"card-title\">").append(movie.title()).append("</h5>\n")
                    .append("            <p class=\"card-text\">\n")
                    .append("                <strong>Ano:</strong> ").append(movie.year()).append("<br>\n")
                    .append("                <strong>Tipo:</strong> ").append(movie.type()).append("<br>\n")
                    .append("                <strong>IMDb ID:</strong> ").append(movie.imdbID()).append("\n")
                    .append("            </p>\n")
                    .append("        </div>\n")
                    .append("    </div>\n")
                    .append("</div>\n");
        }

        // Fecha o HTML do corpo
        String bodyEnd = """
                </div>
            </div>
        </body>
        """;

        // Escreve o HTML completo
        writer.println("<!DOCTYPE html>");
        writer.println("<html lang=\"pt-BR\">");
        writer.println(head);
        writer.println(bodyStart);
        writer.println(moviesHtml.toString());
        writer.println(bodyEnd);
        writer.flush();
        writer.close();
    }
}
