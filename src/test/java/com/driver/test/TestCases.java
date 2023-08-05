package com.driver.test;

import com.driver.EaseMyTrip;
import com.driver.controllers.AirportController;
import com.driver.controllers.AirportService;
import com.driver.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = EaseMyTrip.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {

    private MockMvc mockMvc;
    private AirportService airportService;

    @BeforeEach
    public void setup() {
        // Mocking the AirportService
        airportService = mock(AirportService.class);
        AirportController airportController = new AirportController(airportService);

        // Set up the MockMvc with the AirportController
        mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();
    }
    @Test
    public void testAddAirport() throws Exception {
        // Create an example Airport object
        Airport airport = new Airport("Example Airport", 3, City.DELHI);

        // Set up the mock behavior of the AirportService
        when(airportService.addAirport(airport)).thenReturn("SUCCESS");

        // Perform the HTTP POST request to add the airport
        mockMvc.perform(MockMvcRequestBuilders.post("/add_airport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(airport)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("SUCCESS"));
    }

    // Helper method to convert objects to JSON strings
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

