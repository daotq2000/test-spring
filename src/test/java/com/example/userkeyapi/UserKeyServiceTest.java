package com.example.userkeyapi;

import com.example.userkeyapi.controller.request.UserCreateRequest;
import com.example.userkeyapi.dto.User;
import com.example.userkeyapi.exception.InvalidException;
import com.example.userkeyapi.exception.NotFoundException;
import com.example.userkeyapi.service.UserKeyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserKeyServiceTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserKeyService userKeyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUserKey_Success() throws Exception {
        User userKeyDTO = User.builder().username("user_test")
                .id(1L)
                .code("user_test#1")
                .createdDate(LocalDateTime.of(LocalDate.of(2025,03,25), LocalTime.MIN)).build();
        when(userKeyService.createUserKey(any(UserCreateRequest.class)))
                .thenReturn(userKeyDTO);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userKeyDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user_test"));
    }

    @Test
    void deleteUserKey_Success() throws Exception {
        doNothing().when(userKeyService).deleteUserKey(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        verify(userKeyService, times(1)).deleteUserKey(1L);
    }

    @Test
    void deleteUserKey_NotFound() throws Exception {
//        doNothing().when(userKeyService).deleteUserKey(22343L);
        doThrow(new NotFoundException("Key not found")).when(userKeyService).deleteUserKey(22343L);
        mockMvc.perform(delete("/users/22343"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Key not found"));

    }
    @Test
    void createUserKey_ExceedsLimit() throws Exception {
        User userKeyDTO = User.builder().username("user_test")
                .id(1L)
                .code("user_test#1")
                .createdDate(LocalDateTime.of(LocalDate.of(2025,03,25), LocalTime.MIN)).build();
        when(userKeyService.createUserKey(any(UserCreateRequest.class)))
                .thenThrow(new InvalidException("User already has 5 keys"));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userKeyDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("User already has 5 keys"));
    }
}