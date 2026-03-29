package com.banking.accounts.controller;

import com.banking.accounts.dto.CustomerDetailsDto;
import com.banking.accounts.service.ICustomerDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final ICustomerDetailsService customerDetailsService;

    public CustomerController(ICustomerDetailsService customerDetailsService) {
        this.customerDetailsService = customerDetailsService;
    }

    @GetMapping("fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam String mobileNumber) {
        CustomerDetailsDto customerDetailsDto = customerDetailsService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
