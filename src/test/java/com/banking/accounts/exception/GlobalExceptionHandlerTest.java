package com.banking.accounts.exception;

import com.banking.accounts.common.TestData;
import com.banking.accounts.controller.AccountsController;
import com.banking.accounts.service.IAccountsService;
import com.banking.accounts.service.impl.AccountsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountsController.class)
class GlobalExceptionHandlerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    IAccountsService iAccountsService;

    @Test
    void handleMethodArgumentNotValid() throws Exception {
        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestData.invalidRequest))
                .andExpect(status().isBadRequest());
    }


}