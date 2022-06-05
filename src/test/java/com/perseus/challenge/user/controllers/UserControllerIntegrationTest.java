package com.perseus.challenge.user.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perseus.challenge.user.dtos.CreateUserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integration-test.properties")
class UserControllerIntegrationTest {

    private final String FIRST_NAME = "vincent";

    private final String LAST_NAME = "enyinwa";

    private final String PHONE = "+2348163273611";

    private final String EMAIL = "enyinwa.vincent@gmail.com";

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void testCreateUser() throws Exception {

        List<String> emails = Arrays.asList(EMAIL);
        List<String> phoneNumber = Arrays.asList(PHONE);

        CreateUserDto requestObject = CreateUserDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .emails(emails)
                .phoneNumbers(phoneNumber)
                .build();

        String jsonBody = objectMapper.writeValueAsString(requestObject);

        mockMvc.perform(post("/api/v1/users/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").exists());

    }

    @Test
    void testGetUserById() throws Exception {

        mockMvc.perform(get("/api/v1/users/{userId}", 2)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.emails").exists())
                .andExpect(jsonPath("$.emails.[0].id").exists())
                .andExpect(jsonPath("$.phoneNumbers.[0].id").exists());
    }

    @Test
    void testGetUserById_whenIdDoesNotExist() throws Exception {

        mockMvc.perform(get("/api/v1/users/{userId}", 10000)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testGetUserByName() throws Exception {

        mockMvc.perform(get("/api/v1/users/find")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .param("firstName", FIRST_NAME))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").exists())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result[0].firstName", FIRST_NAME).exists());
    }

    @Test
    void testDeleteUser() throws Exception {

        mockMvc.perform(delete("/api/v1/users/{userId}", 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser_whenUserNotFound() throws Exception {
        mockMvc.perform(delete("/api/v1/users/{userId}", 1000000)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().is5xxServerError());
    }


}