package com.orlandowatanabe.interfaces;

import java.util.List;

public interface JsonParser<T> {
        List<? extends Content> parse(String json);
}
