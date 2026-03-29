package com.banking.accounts.service.client;

import com.banking.accounts.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(LoansFeignClient.LOANS_MICROSERVICE_NAME)
public interface LoansFeignClient {
    String LOANS_MICROSERVICE_NAME = "loans";

    @GetMapping("/api/fetch")
    ResponseEntity<LoanDto> fetch(@RequestParam String mobileNumber);
}
