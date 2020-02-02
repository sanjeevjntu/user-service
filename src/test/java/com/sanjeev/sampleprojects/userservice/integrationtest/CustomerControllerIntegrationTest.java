package com.sanjeev.sampleprojects.userservice.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanjeev.sampleprojects.userservice.dto.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(username = "user", roles = {"ADMIN"})
    @Test
    void givenAuthRequestAndCustomerDto_thenReturn201() throws Exception {

        CustomerDto dtoMock = CustomerDto.builder().vin("JTKKU10479J033714").name("sanjeev").build();
        String dtoString = new ObjectMapper().writeValueAsString(dtoMock);
        dtoMock.setId(UUID.randomUUID());

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoString)
                );
        resultActions
                .andExpect(status().isCreated())
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


