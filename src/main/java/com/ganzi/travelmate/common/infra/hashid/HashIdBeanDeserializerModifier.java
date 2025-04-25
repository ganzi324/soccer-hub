package com.ganzi.travelmate.common.infra.hashid;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class HashIdBeanDeserializerModifier extends BeanDeserializerModifier {

    private final HashIdService hashIdService;

    @Override
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config,
                                                  BeanDescription beanDesc,
                                                  JsonDeserializer<?> deserializer) {
        return new StdDeserializer<>(deserializer.handledType()) {
            @Override
            public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                Object instance = deserializer.deserialize(p, ctxt);

                // @HashId가 붙은 필드에 대해 HashIdDeserializer 적용
                beanDesc.findProperties().forEach(prop -> {
                    if (prop.getField().getAnnotation(HashId.class) != null && prop.getField().getDeclaringClass() == Long.class) {
                        try {
                            String encodedId = (String) prop.getGetter().call1(instance);
                            if (encodedId != null) {
                                Long decodedId = hashIdService.decode(encodedId);
                                prop.getSetter().setValue(instance, decodedId);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to decode HashId", e);
                        }
                    }
                });

                return instance;
            }
        };
    }
}