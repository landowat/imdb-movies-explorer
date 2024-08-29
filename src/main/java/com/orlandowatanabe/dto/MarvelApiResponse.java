package com.orlandowatanabe.dto;

import com.orlandowatanabe.models.Marvel;

import java.util.List;

public class MarvelApiResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        private List<Marvel> results;

        public List<Marvel> getResults() {
            return results;
        }
    }
}