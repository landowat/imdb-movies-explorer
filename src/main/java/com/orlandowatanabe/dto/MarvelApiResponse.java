package com.orlandowatanabe.dto;

import com.orlandowatanabe.models.Series;
import lombok.Getter;

import java.util.List;

@Getter
public class MarvelApiResponse {
    private Data data;

    @Getter
    public static class Data {
        private List<Series> results;

    }
}