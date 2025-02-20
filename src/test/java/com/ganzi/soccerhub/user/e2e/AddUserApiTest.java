package com.ganzi.soccerhub.user.e2e;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class AddUserApiTest {

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
                .post("/v1/auth/sign-in")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}
