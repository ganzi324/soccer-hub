package com.ganzi.soccerhub.common.infra.hashid;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HashIdBeanDeserializerModifier extends BeanDeserializerModifier {

    private final HashIdService hashIdService;

    @Override
    public BeanDeserializerBuilder updateBuilder(DeserializationConfig config, BeanDescription beanDesc, BeanDeserializerBuilder builder) {
        List<SettableBeanProperty> properties = new ArrayList<>();
        builder.getProperties().forEachRemaining(properties::add);

        for (SettableBeanProperty property : properties) {
            if (!property.getType().hasRawClass(Long.class)) {
                continue;
            }

            Optional.ofNullable(property.getAnnotation(HashId.class))
                    .ifPresent(annotation -> {
                        SettableBeanProperty newProperty = property.withValueDeserializer(new JsonDeserializer<Long>() {
                            @Override
                            public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                                return hashIdService.decode(p.getValueAsString());
                            }
                        });
                        builder.addProperty(newProperty);
                    });
        }

        return builder;
    }
}