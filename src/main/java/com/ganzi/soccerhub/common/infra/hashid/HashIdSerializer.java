package com.ganzi.soccerhub.common.infra.hashid;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class HashIdSerializer extends JsonSerializer<Object> {

    private final HashIdService hashIdService;

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value instanceof Long longValue) {
            String encodedId = hashIdService.encode(longValue);
            gen.writeString(encodedId);
        } else {
            gen.writeNull();
        }
    }
}