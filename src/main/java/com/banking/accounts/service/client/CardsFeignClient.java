package com.banking.accounts.service.client;

import com.banking.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(CardsFeignClient.CARDS_MICROSERVICE_NAME)
public interface CardsFeignClient {
    String CARDS_MICROSERVICE_NAME = "cards";

    @GetMapping("/api/fetch")
    ResponseEntity<CardDto> fetch(@RequestParam String mobileNumber);
}
