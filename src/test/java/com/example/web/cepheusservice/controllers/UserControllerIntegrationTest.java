package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.TestDataUtil;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.services.UserServise;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerIntegrationTest {

    private MockMvc mockMvc;
    private UserServise userServise;
    private ObjectMapper objectMapper;

    @Autowired
    public UserControllerIntegrationTest(MockMvc mockMvc, UserServise userServise, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.userServise = userServise;
        this.objectMapper = objectMapper;
    }

//    @Test
//    public void testThatCreateUserSuccessfullyReturnsHttp201Created() throws Exception {
//        UserEntity user = TestDataUtil.createUserEntity();
//        user.setId(null);
//        String userJosn = objectMapper.writeValueAsString(user);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userJosn)
//        ).andExpect(MockMvcResultMatchers.status().isCreated());
//    }

}
