package com.ganzi.travelmate.user.controller;

import com.ganzi.travelmate.common.config.TestDataSourceConfig;
import com.ganzi.travelmate.common.infra.persistence.JpaConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(JpaConfig.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class AddUserControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;

    @BeforeAll
    public void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void addUser_thenOk(){
        String jsonBody = """
                        {
                            "name": "Jisu",
                            "email": "ganzi324@gmail.com",
                            "password": "Qwer1234!"
                        }
                        """;
        RestAssured
                .with()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post("/api/v1/auth/sign-in")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}
