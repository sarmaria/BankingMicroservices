package com.banking.accounts.controller;

import com.banking.accounts.constants.AccountConstants;
import com.banking.accounts.dto.CustomerDetailsDto;
import com.banking.accounts.service.ICustomerDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ICustomerDetailsService customerDetailsService;

    public CustomerController(ICustomerDetailsService customerDetailsService) {
        this.customerDetailsService = customerDetailsService;
    }

    @GetMapping("fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader(name = AccountConstants.TRACE_ID, required = false) String traceId, @RequestParam String mobileNumber) {
        logger.debug("Trace-Id of the current request {}", traceId);
        CustomerDetailsDto customerDetailsDto = customerDetailsService.fetchCustomerDetails(mobileNumber, traceId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
