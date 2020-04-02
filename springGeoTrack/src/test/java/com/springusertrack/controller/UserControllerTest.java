package com.springusertrack.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springusertrack.dto.UserDto;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import utils.GeoTrackMySQLContainer;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {GeoTrackMySQLContainer.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    public void indexRequestShouldReturnTwoUsers() {
        String resultJson = mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<UserDto> users = objectMapper.readValue(resultJson, new TypeReference<List<UserDto>>() {});

        assertEquals(2, users.size());
    }

    @Test
    @SneakyThrows
    public void getRequestShouldReturnFirstUser() {
        String resultJson = mvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto user = objectMapper.readValue(resultJson, UserDto.class);

        assertEquals(1, user.getId());
        assertEquals("first user", user.getName());
        assertEquals("first@mail.com", user.getEmail());
    }

    @Test
    @SneakyThrows
    public void createRequestShouldCreteNewUser() {
        UserDto userDtoRequest = new UserDto();
        userDtoRequest.setName("test user");
        userDtoRequest.setEmail("test@test.test");

        String userDtoJson = objectMapper.writeValueAsString(userDtoRequest);

        String resultJson = mvc.perform(MockMvcRequestBuilders
                .post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto userDtoResponse = objectMapper.readValue(resultJson, UserDto.class);

        assertNotNull(userDtoResponse.getId());
        assertEquals(userDtoRequest.getName(), userDtoResponse.getName());
        assertEquals(userDtoRequest.getEmail(), userDtoResponse.getEmail());
    }

    @Test
    @SneakyThrows
    public void updateRequestShouldUpdateExistUser() {
        UserDto userDtoRequest = new UserDto();
        userDtoRequest.setId(2);
        userDtoRequest.setName("changed user");
        userDtoRequest.setEmail("changed@mail.com");

        String userDtoJson = objectMapper.writeValueAsString(userDtoRequest);

        mvc.perform(MockMvcRequestBuilders
                .put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String resultJson = mvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto userDtoResponse = objectMapper.readValue(resultJson, UserDto.class);

        assertEquals(userDtoRequest.getId(), userDtoResponse.getId());
        assertEquals(userDtoRequest.getName(), userDtoResponse.getName());
        assertEquals(userDtoRequest.getEmail(), userDtoResponse.getEmail());
    }

    @Test
    @SneakyThrows
    public void deleteRequestShouldDeleteUser() {
        mvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        mvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }
}