package com.orlandowatanabe.parsers;

import com.google.gson.Gson;
import com.orlandowatanabe.dto.MarvelApiResponse;
import com.orlandowatanabe.interfaces.JsonParser;
import com.orlandowatanabe.models.Marvel;

import java.util.List;

public class MarvelComicJsonParser implements JsonParser<Marvel> {

    @Override
    public List<Marvel> parse(String json) {
        Gson gson = new Gson();
        MarvelApiResponse response = gson.fromJson(json, MarvelApiResponse.class);

        List<Marvel> comics = response.getData().getResults();

        return comics;
    }
}
