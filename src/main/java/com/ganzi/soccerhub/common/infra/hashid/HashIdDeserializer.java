package com.ganzi.soccerhub.common.infra.hashid;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
@RequiredArgsConstructor
public class HashIdDeserializer extends JsonDeserializer<Long> {

    private final HashIdService hashIdService;

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String encodedId = p.getValueAsString();
        return (encodedId != null) ? hashIdService.decode(encodedId) : null;
    }
}