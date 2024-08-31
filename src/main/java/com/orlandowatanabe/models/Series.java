package com.orlandowatanabe.models;

import com.orlandowatanabe.interfaces.Content;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Series implements Content, Comparable<Series> {
    private String title;
    private Thumbnail thumbnail;
    private List<Date> dates;

    @Override
    public String urlImage() {
        return thumbnail != null ? thumbnail.getPath() + "." + thumbnail.getExtension() : "N/A";
    }

    @Override
    public String rating() {
        return "N/A";
    }

    @Override
    public String type() {
        return "Series";
    }

    @Override
    public String year() {
        return dates != null ? dates.stream()
                .filter(date -> "onsaleDate".equals(date.getType()))
                .findFirst()
                .map(date -> date.getDate().substring(0, 4))
                .orElse("Unknown") : "Unknown";
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public int compareTo(Series other) {
        return this.title.compareTo(other.title);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Thumbnail {
        private String path;
        private String extension;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Date {
        private String type;
        private String date;
    }
}
