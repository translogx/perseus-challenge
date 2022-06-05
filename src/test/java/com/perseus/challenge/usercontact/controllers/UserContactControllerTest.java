package com.perseus.challenge.usercontact.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perseus.challenge.exceptionHandlers.ApiAdvice;
import com.perseus.challenge.user.controllers.UserController;
import com.perseus.challenge.user.dtos.CreateUserDto;
import com.perseus.challenge.user.models.UserEntity;
import com.perseus.challenge.usercontact.Interface.UserContactService;
import com.perseus.challenge.usercontact.service.UserContactServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserContactControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserContactServiceImpl userContactService;

    @InjectMocks
    private UserContactController userContactController;

    @BeforeEach
    void setUp() {
        log.info("setting things up");
        mockMvc = standaloneSetup(userContactController)
                .setControllerAdvice(new ApiAdvice())
                .build();
    }

    @Test
    void addEmail() throws Exception {

        doNothing().when(userContactService).addEmail(anyInt(), anyString());

        mockMvc.perform(post("/api/v1/contact/1/add-email")
                        .param("email", "enyinwa.vincent@outlook.com")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void addPhoneNumber() throws Exception {

        doNothing().when(userContactService).addPhoneNumber(anyInt(), anyString());

        mockMvc.perform(post("/api/v1/contact/1/add-phone-number")
                        .param("phoneNumber", "+2348163210000484")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmail() throws Exception {

        doNothing().when(userContactService).updateEmail(anyInt(), anyInt(), anyString());

        mockMvc.perform(put("/api/v1/contact/1/email/{emailId}", 1)
                        .param("email", "enyinwa.vincent@outlook.com")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updatePhoneNumber() throws Exception {

        doNothing().when(userContactService).updatePhoneNumber(anyInt(), anyInt(), anyString());

        mockMvc.perform(put("/api/v1/contact/1/phone-number/{phoneNumberId}", 1)
                        .param("phoneNumber", "+2348163210000484")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}