package com.orlandowatanabe.models;

import com.orlandowatanabe.interfaces.Content;

public class Movie implements Content {
    private final String title;
    private final String year;
    private final String imdbID;
    private final String type;
    private final String urlImage;
    private final String rating;

    public Movie(String title, String year, String imdbID, String type, String urlImage, String rating) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.type = type;
        this.urlImage = urlImage;
        this.rating = rating;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String year() {
        return year;
    }

    @Override
    public String urlImage() {
        return urlImage;
    }

    @Override
    public String rating() {
        return "N/A";
    }
}
