package com.ganzi.travelmate.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ganzi.travelmate.common.infra.hashid.HashIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class AbstractWebMvcTest {

    @Autowired
    protected MockMvc mvc;

    protected final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @MockBean
    protected HashIdService hashIdService;
}
