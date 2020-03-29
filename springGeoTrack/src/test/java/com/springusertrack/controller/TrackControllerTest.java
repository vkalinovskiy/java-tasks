package com.springusertrack.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springusertrack.dto.TrackDto;
import lombok.SneakyThrows;
import org.junit.Before;
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

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {GeoTrackMySQLContainer.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TrackControllerTest {
    @Autowired
    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    @SneakyThrows
    public void createRequestShouldCreateNewTrack() {
        TrackDto trackDto = new TrackDto();
        trackDto.setLat(73.374437);
        trackDto.setLng(54.979878);
        trackDto.setUserId(1);

        String trackDtoJson = objectMapper.writeValueAsString(trackDto);

        String resultJson = mvc.perform(MockMvcRequestBuilders
                .post("/tracks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(trackDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        TrackDto trackDtoResponse = objectMapper.readValue(resultJson, TrackDto.class);

        assertEquals("RU", trackDtoResponse.getCountryCode());
        assertEquals(644024, trackDtoResponse.getPostalCode());
        assertEquals("Россия", trackDtoResponse.getCountry());
        assertEquals("Омская область", trackDtoResponse.getProvince());
        assertEquals("Омск", trackDtoResponse.getCity());
        assertEquals("Ильинская улица", trackDtoResponse.getStreet());
        assertEquals("4", trackDtoResponse.getHouse());
    }

    @Test
    @SneakyThrows
    public void getRequestShouldReturnTracks() {
        TrackDto trackDto = new TrackDto();
        trackDto.setLat(73.374437);
        trackDto.setLng(54.979878);
        trackDto.setUserId(1);

        String trackDtoJson = objectMapper.writeValueAsString(trackDto);

        mvc.perform(MockMvcRequestBuilders
                .post("/tracks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(trackDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);


        LocalDateTime dateFrom = LocalDateTime.of(2020, Month.FEBRUARY, 1, 0, 0, 0);
        LocalDateTime dateTo = LocalDateTime.of(2020, Month.MAY, 1, 0, 0, 0);


        String resultJson = mvc.perform(MockMvcRequestBuilders
                .get("/tracks/")
                .param("user_id", "1")
                .param("date_from", dateFrom.format(DateTimeFormatter.ISO_DATE_TIME))
                .param("date_to", dateTo.format(DateTimeFormatter.ISO_DATE_TIME)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        List<TrackDto> tracks = objectMapper.readValue(resultJson, new TypeReference<List<TrackDto>>() {});

        assertEquals(1, tracks.size());
    }
}
