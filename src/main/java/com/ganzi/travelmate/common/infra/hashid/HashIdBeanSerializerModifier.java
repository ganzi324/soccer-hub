package com.ganzi.travelmate.common.infra.hashid;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class HashIdBeanSerializerModifier extends BeanSerializerModifier {

    private final HashIdService hashIdService;

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        for (BeanPropertyWriter writer : beanProperties) {
            if (writer.getAnnotation(HashId.class) != null) {
                writer.assignSerializer(new HashIdSerializer(hashIdService));
            }
        }
        return beanProperties;
    }
}
