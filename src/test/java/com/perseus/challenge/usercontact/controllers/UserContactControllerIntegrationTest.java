package com.perseus.challenge.usercontact.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perseus.challenge.exceptionHandlers.ApiAdvice;
import com.perseus.challenge.user.dtos.CreateUserDto;
import com.perseus.challenge.usercontact.Interface.UserContactService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integration-test.properties")
class UserContactControllerIntegrationTest {

    private final String FIRST_NAME = "vincent";

    private final String LAST_NAME = "enyinwa";

    private final String PHONE = "+2348163273611";

    private final String EMAIL = "enyinwa.vincent@gmail.com";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() throws Exception {


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
    void addEmail() throws Exception {

        mockMvc.perform(post("/api/v1/contact/1/add-email")
                        .param("email", EMAIL)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void addPhoneNumber() throws Exception {

        mockMvc.perform(post("/api/v1/contact/1/add-phone-number")
                        .param("phoneNumber", PHONE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmail() throws Exception {

        mockMvc.perform(put("/api/v1/contact/1/email/{emailId}", 1)
                        .param("email", EMAIL)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmail_whenEmailIdDoesNotBelongToSpecifiedUser() throws Exception {

        mockMvc.perform(put("/api/v1/contact/1/email/{emailId}", 100000)
                        .param("email", EMAIL)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updatePhoneNumber() throws Exception {

        mockMvc.perform(put("/api/v1/contact/1/phone-number/{phoneNumberId}", 1)
                        .param("phoneNumber", PHONE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updatePhoneNumber_whenPhoneNumberIdDoesNotBelongToSpecifiedUser() throws Exception {

        mockMvc.perform(put("/api/v1/contact/1/phone-number/{phoneNumberId}", 100000)
                        .param("phoneNumber", PHONE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}