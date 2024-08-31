package com.orlandowatanabe.parsers;

import com.google.gson.Gson;
import com.orlandowatanabe.dto.MarvelApiResponse;
import com.orlandowatanabe.interfaces.JsonParser;
import com.orlandowatanabe.models.Series;

import java.util.List;

public class MarvelComicJsonParser implements JsonParser<Series> {

    @Override
    public List<Series> parse(String json) {
        Gson gson = new Gson();
        MarvelApiResponse response = gson.fromJson(json, MarvelApiResponse.class);

        List<Series> series = response.getData().getResults();

        return series;
    }
}
