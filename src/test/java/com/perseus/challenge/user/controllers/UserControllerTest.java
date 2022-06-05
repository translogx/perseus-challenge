package com.perseus.challenge.user.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perseus.challenge.exceptionHandlers.ApiAdvice;
import com.perseus.challenge.exceptionHandlers.RequestException;
import com.perseus.challenge.exceptionHandlers.ServerRuntimeException;
import com.perseus.challenge.user.dtos.CreateUserDto;
import com.perseus.challenge.user.interfaces.UserService;
import com.perseus.challenge.user.models.UserEntity;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        log.info("setting things up");
        mockMvc = standaloneSetup(userController)
                .setControllerAdvice(new ApiAdvice())
                .build();
    }

    @Test
    void testCreateUser() throws Exception {

        List<String> emails = Arrays.asList("enyinwa.vincent@gmail.com");
        List<String> phoneNumber = Arrays.asList("+2348163273611");

        CreateUserDto requestObject = CreateUserDto.builder()
                .firstName("vincent")
                .lastName("enyinwa")
                .emails(emails)
                .phoneNumbers(phoneNumber)
                .build();

        String jsonBody = objectMapper.writeValueAsString(requestObject);

        log.info(jsonBody);

        UserEntity responseObject = UserEntity.builder().id(1).build();

        when(userService.createNewUser(any())).thenReturn(responseObject);

        mockMvc.perform(post("/api/v1/users/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    void testGetUserById() throws Exception {
        UserEntity responseObject = UserEntity.builder().id(1).build();

        when(userService.getUserById(anyInt())).thenReturn(responseObject);
        mockMvc.perform(get("/api/v1/users/{userId}", 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testGetUserById_whenIdDoesNotExist() throws Exception {
        UserEntity responseObject = UserEntity.builder().id(1).build();

        when(userService.getUserById(anyInt())).thenThrow( new RequestException("not found"));

        mockMvc.perform(get("/api/v1/users/{userId}", 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testGetUserByName() throws Exception {
        UserEntity responseObject = UserEntity.builder().id(1).build();
        Map<String, List<UserEntity>> res = new HashMap<>();
        res.put("result", Arrays.asList(responseObject));

        when(userService.getUserByName(any(), any())).thenReturn(res);

        mockMvc.perform(get("/api/v1/users/find", 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .param("firstName", "vincent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").exists());
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(anyInt());

        mockMvc.perform(delete("/api/v1/users/{userId}", 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser_whenUserNotFound() throws Exception {
        doThrow(new RequestException("not found")).when(userService).deleteUser(anyInt());

        mockMvc.perform(delete("/api/v1/users/{userId}", 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().is4xxClientError());
    }


    @Test
    void testDeleteUser_whenSqlExceptionOccursWhileDeleting() throws Exception {
        doThrow(new ServerRuntimeException("Failed to delete user")).when(userService).deleteUser(anyInt());

        mockMvc.perform(delete("/api/v1/users/{userId}", 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().is5xxServerError());
    }
}