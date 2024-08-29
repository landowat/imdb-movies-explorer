package com.orlandowatanabe.presentation;

import com.orlandowatanabe.interfaces.Content;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator<T extends Content> {
    private final PrintWriter writer;

    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    public void generate(List<T> contentList) {
        String head = """
        <head>
            <meta charset="utf-8">
            <title>Imdb Movies Explorer</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <style>
                body {
                    background-color: #282a36;
                    color: #f8f8f2;
                }
        
                .card-img-top {
                    height: 550px !important;
                    object-fit: cover !important;
                }
        
                .card-body {
                    background-color: #44475a !important;
                }
        
                .card-title {
                    color: #bd93f9 !important;
                }
        
                .card-text {
                    color: #f8f8f2 !important;
                }
            </style>
        </head>
        """;

        String bodyStart = """
        <body>
            <div class="container">
                <h1 class="my-4 text-center">Lista de Filmes</h1>
                <div class="row">
        """;

        StringBuilder moviesHtml = new StringBuilder();
        for (Content content : contentList) {
            moviesHtml.append("<div class=\"col-md-4 mb-4\">\n")
                    .append("    <div class=\"card\">\n")
                    .append("        <img src=\"").append(content.urlImage()).append("\" class=\"card-img-top\" alt=\"Poster do Filme\">\n")
                    .append("        <div class=\"card-body\">\n")
                    .append("            <h5 class=\"card-title\">").append(content.title()).append("</h5>\n")
                    .append("            <p class=\"card-text\">\n")
                    .append("                <strong>Ano:</strong> ").append(content.year()).append("<br>\n")
                    .append("                <strong>Nota:</strong> ").append(content.rating()).append("<br>\n")
                    .append("            </p>\n")
                    .append("        </div>\n")
                    .append("    </div>\n")
                    .append("</div>\n");
        }

        String bodyEnd = """
                </div>
            </div>
        </body>
        """;

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
