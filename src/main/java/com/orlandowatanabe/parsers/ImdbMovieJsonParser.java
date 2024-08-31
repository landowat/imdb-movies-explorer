package com.orlandowatanabe.parsers;

import com.google.gson.Gson;
import com.orlandowatanabe.dto.ImdbApiResponse;
import com.orlandowatanabe.interfaces.JsonParser;
import com.orlandowatanabe.models.Movie;

import java.util.List;

public class ImdbMovieJsonParser implements JsonParser<Movie> {

    @Override
    public List<Movie> parse(String json) {
        Gson gson = new Gson();
        ImdbApiResponse response = gson.fromJson(json, ImdbApiResponse.class);

        if (response == null || response.getSearch() == null) {
            return List.of();
        }

        List<Movie> movies = response.getSearch();

        return movies;
    }
}