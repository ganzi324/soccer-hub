package com.ganzi.soccerhub.common.infra.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ganzi.soccerhub.common.infra.hashid.HashIdBeanDeserializerModifier;
import com.ganzi.soccerhub.common.infra.hashid.HashIdBeanSerializerModifier;
import com.ganzi.soccerhub.common.infra.hashid.HashIdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(HashIdService hashIdService) {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new HashIdBeanSerializerModifier(hashIdService));
        module.setDeserializerModifier(new HashIdBeanDeserializerModifier(hashIdService));

        objectMapper.registerModule(module);
        return objectMapper;
    }
}