package com.fordav.autonomous.userservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fordav.autonomous.userservice.controller.Customer;
import com.fordav.autonomous.userservice.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class CustomerControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @BeforeEach
    public void before() {

    }

    @Test
    @DisplayName("shouldSuccessfullyCreateCustomer")
    public void shouldSuccessfullyCreateCustomer() throws Exception {
        Customer mock = Customer.builder().vin("VIN1234").name("sanjeev").build();
        String writeValueAsString =  new ObjectMapper().writeValueAsString(mock);
        mock.setId(UUID.randomUUID());

        when(customerService.create(any(Customer.class))).thenReturn(mock);
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString)
                );
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("sanjeev"))
                .andExpect(jsonPath("$.vin").value("VIN1234"))
                .andExpect(jsonPath("$.id").isNotEmpty());

    }
}
