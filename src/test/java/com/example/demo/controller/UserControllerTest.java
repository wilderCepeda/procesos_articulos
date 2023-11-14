package com.example.demo.controller;

import com.example.demo.user.controller.UserController;
import com.example.demo.user.dto.UserRequest;
import com.example.demo.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void existMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("angel@gmail.com");
        userRequest.setFirstName("Angel");
        userRequest.setLastName("Perez");
        userRequest.setAddress("Rio de oro");
        userRequest.setPhoneNumber("123456");
        userRequest.setPassword("Admin");

        mockMvc.perform(post("/api/user").contentType("application/json").content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenNullValue_thenReturns400() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("angel@gmail.com");
        userRequest.setFirstName("Angel");

        mockMvc.perform(post("/api/user").contentType("application/json").content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenValidInput_thenReturnsUserResource() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("felipe@gmail.com");
        userRequest.setFirstName("felipe");
        userRequest.setLastName("montaño");
        userRequest.setAddress("Rio de oro");
        userRequest.setPhoneNumber("123456");
        userRequest.setPassword("Admin");

        MvcResult mvcResult = mockMvc.perform(post("/api/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        User user = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);

        assertThat(user.getEmail()).isEqualTo(userRequest.getEmail());
    }

}
