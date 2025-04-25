package com.ganzi.travelmate.common.infra.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerListToLongListDeserializer extends JsonDeserializer<List<Long>> {

    @Override
    public List<Long> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<Integer> intList = new ObjectMapper().readValue(p, new TypeReference<List<Integer>>() {});
        return (intList != null)
                ? intList.stream().map(Integer::longValue).collect(Collectors.toList())
                : null;
    }
}