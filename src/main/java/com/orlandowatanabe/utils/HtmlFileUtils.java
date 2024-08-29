package com.orlandowatanabe.utils;

import com.orlandowatanabe.interfaces.Content;
import com.orlandowatanabe.presentation.HTMLGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Supplier;

public class HtmlFileUtils {
    public static <T extends Content> void writeToHtml(List<T> contentList, String filePath, Supplier<HTMLGenerator<T>> generatorSupplier) throws IOException {
        File outputDirectory = new File(filePath).getParentFile();
        Utils.checkDirectory(outputDirectory);

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            HTMLGenerator<T> generator = generatorSupplier.get();
            generator.generate(contentList);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
