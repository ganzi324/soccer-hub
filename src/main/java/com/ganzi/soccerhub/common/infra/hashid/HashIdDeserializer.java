package com.ganzi.soccerhub.common.infra.hashid;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import java.io.IOException;

public class HashIdDeserializer extends StdScalarDeserializer<Object> {

    private final HashIdService hashIdService;

    public HashIdDeserializer(HashIdService hashIdService) {
        super(Object.class);
        this.hashIdService = hashIdService;
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String hash = p.getValueAsString();
        return hashIdService.decode(hash);
    }
}