package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void existMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void validateAuth() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/article").contentType("application/json"))
                .andExpect(status().isUnauthorized()).andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat("Acceso no autorizado").isEqualTo(actualResponseBody.substring(12, 32));

    }
}
