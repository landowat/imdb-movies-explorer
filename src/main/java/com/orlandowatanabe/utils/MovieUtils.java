package com.orlandowatanabe.utils;

import com.orlandowatanabe.models.Movie;
import com.orlandowatanabe.presentation.HTMLGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

public class MovieUtils {

    public static void writeMoviesToHtml(List<Movie> movies, String filePath) {
        File outputDirectory = new File(filePath).getParentFile();

        // Criar diretorio output caso nao exista
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
