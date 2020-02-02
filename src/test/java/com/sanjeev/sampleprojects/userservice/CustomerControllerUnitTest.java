package com.sanjeev.sampleprojects.userservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanjeev.sampleprojects.userservice.controller.CustomerController;
import com.sanjeev.sampleprojects.userservice.domain.Customer;
import com.sanjeev.sampleprojects.userservice.dto.CustomerDto;
import com.sanjeev.sampleprojects.userservice.mapper.CustomerMapper;
import com.sanjeev.sampleprojects.userservice.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@ExtendWith(SpringExtension.class)
public class CustomerControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerMapper customerMapper;

    @BeforeEach
    public void before() {

    }

    @WithMockUser(username = "user", roles = {"ADMIN"})
    @Test
    @DisplayName("shouldSuccessfullyCreateCustomer")
    public void shouldSuccessfullyCreateCustomer() throws Exception {
        CustomerDto dtoMock = CustomerDto.builder().vin("JTKKU10479J033714").name("sanjeev").build();
        String dtoString = new ObjectMapper().writeValueAsString(dtoMock);
        dtoMock.setId(UUID.randomUUID());

        Customer customerMock = Customer.builder()
                .vin("JTKKU10479J033714").name("sanjeev").id(UUID.randomUUID())
                .build();
        when(customerMapper.toDomain(any(CustomerDto.class))).thenReturn(customerMock);
        when(customerService.create(any(Customer.class))).thenReturn(customerMock);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoString)
                );
        resultActions
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("sanjeev"))
//                .andExpect(jsonPath("$.vin").value("VIN1234"))
//                .andExpect(jsonPath("$.id").isNotEmpty())
                //.andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());
    }


    @Test
    void givenAuthRequestAndCustomerDto_thenReturnUnAuthorized() throws Exception {

        CustomerDto dtoMock = CustomerDto.builder().vin("JTKKU10479J033714").name("sanjeev").build();
        String dtoString = new ObjectMapper().writeValueAsString(dtoMock);
        dtoMock.setId(UUID.randomUUID());

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoString)
                );
        resultActions
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @WithMockUser(username = "user", roles = {"WRONG_ROLE"})
    @Test
    void givenAuthRequestAndCustomerDto_thenReturnForbidden() throws Exception {

        CustomerDto dtoMock = CustomerDto.builder().vin("JTKKU10479J033714").name("sanjeev").build();
        String dtoString = new ObjectMapper().writeValueAsString(dtoMock);
        dtoMock.setId(UUID.randomUUID());

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoString)
                );
        resultActions
                .andExpect(status().isForbidden())
                .andDo(print());
    }
}
