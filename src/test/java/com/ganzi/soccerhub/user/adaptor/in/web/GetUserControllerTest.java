package com.ganzi.soccerhub.user.adaptor.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzi.soccerhub.common.UserTestData;
import com.ganzi.soccerhub.user.application.port.in.GetUserQuery;
import com.ganzi.soccerhub.user.application.response.UserResponse;
import com.ganzi.soccerhub.user.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(GetUserController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetUserControllerTest {

    @MockBean
    private GetUserQuery getUserQuery;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<UserResponse> json;

    @BeforeAll
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @WithMockUser
    void getUser_thenOk() throws Exception {
        User user = UserTestData.defaultUser();

        // given
        given(getUserQuery.getUserById(any(User.UserId.class)))
                .willReturn(Optional.of(user));

        // when
        MockHttpServletResponse response = mvc.perform(
                        get("/v1/users/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .contains(json.write(UserResponse.of(user)).getJson());
    }
}
