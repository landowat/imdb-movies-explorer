package com.orlandowatanabe.models;

import com.orlandowatanabe.interfaces.Content;

import java.util.List;

public class Marvel implements Content {
    private String title;
    private Thumbnail thumbnail;
    private List<Date> dates;

    @Override
    public String title() {
        return title;
    }

    @Override
    public String urlImage() {
        return thumbnail.getPath() + "." + thumbnail.getExtension();
    }

    @Override
    public String rating() {
        return "N/A";
    }

    @Override
    public String year() {
        return dates.stream()
                .filter(date -> "onsaleDate".equals(date.getType()))
                .findFirst()
                .map(date -> date.getDate().substring(0, 4))
                .orElse("Unknown");
    }

    public static class Thumbnail {
        private String path;
        private String extension;

        public String getPath() {
            return path;
        }

        public String getExtension() {
            return extension;
        }
    }

    public static class Date {
        private String type;
        private String date;

        public String getType() {
            return type;
        }

        public String getDate() {
            return date;
        }
    }
}

