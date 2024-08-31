package com.orlandowatanabe.dto;

import com.orlandowatanabe.models.Movie;
import lombok.Data;
import java.util.List;

@Data
public class ImdbApiResponse {
    private List<Movie> Search;
    private String totalResults;
    private String Response;
}
