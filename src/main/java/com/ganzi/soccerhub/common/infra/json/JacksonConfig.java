package com.ganzi.soccerhub.common.infra.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ganzi.soccerhub.common.infra.hashid.HashIdBeanSerializerModifier;
import com.ganzi.soccerhub.common.infra.hashid.HashIdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder, HashIdService hashIdService) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new HashIdBeanSerializerModifier(hashIdService));
//        module.setDeserializerModifier(new HashIdBeanDeserializerModifier(hashIdService));
        objectMapper.registerModule(module);
        return objectMapper;
    }
}