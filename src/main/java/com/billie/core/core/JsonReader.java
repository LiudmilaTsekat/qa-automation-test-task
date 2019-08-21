package com.billie.core.core;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonReader {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> List<T> deserializeArrayResponse(CoreResponse response, Class<T> tClass) {
        try {
            MappingIterator<T> iterator = mapper.readerFor(tClass).readValues(response.body);
            return iterator.readAll();
        } catch (IOException e) {
            throw new BillieException("Impossibility to deserialize [" + tClass.getName() + "] from response [" + response.body + "] \n" +
                    " Due to :" + e.getMessage());
        }
    }
}
