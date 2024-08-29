package com.orlandowatanabe.utils;

import java.io.File;

public class Utils {
    public static void checkDirectory(File outputDirectory) {

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
    }
}
