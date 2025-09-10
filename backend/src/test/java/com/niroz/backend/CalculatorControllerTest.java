package com.niroz.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService service;

    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    void testCalculateApi() throws Exception {
        CalculationRequest req = new CalculationRequest();
        req.setX(2.0);
        req.setY(3.0);
        req.setOp("add");

        when(service.calculate("add", 2.0, 3.0)).thenReturn(5.0);

        mockMvc.perform(post("/api/calc")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5.0));
    }
}