package com.orlandowatanabe.models;

import com.google.gson.annotations.SerializedName;
import com.orlandowatanabe.interfaces.Content;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Content, Comparable<Movie> {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("Poster")
    private String urlImage;

    private String rating;

    @Override
    public String title() {
        return title;
    }

    @Override
    public String year() {
        return year;
    }

    @Override
    public String type() {
        return "Movie";
    }

    @Override
    public String urlImage() {
        return urlImage;
    }

    @Override
    public String rating() {
        return rating != null ? rating : "N/A";
    }

    @Override
    public int compareTo(Movie other) {
        return this.title.compareTo(other.title);
    }
}
