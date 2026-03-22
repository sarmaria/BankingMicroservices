package com.banking.accounts.controller;

import com.banking.accounts.common.TestData;
import com.banking.accounts.dto.CustomerDto;
import com.banking.accounts.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccountsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenValidInputCreateCustomerAccount() throws Exception {
        CustomerDto customer = new CustomerDto("Saran", "saran@gmail.com", "1234567891", null);
        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated());
    }

    @Test
    void givenValidMobileNumberGetCustomerAccountDetails() throws Exception {
        CustomerDto customer = new CustomerDto("Saran", "saran@gmail.com", "1234567891", null);
        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/api/fetch")
                        .param("mobileNumber", "1234567891"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Saran"));
    }
}