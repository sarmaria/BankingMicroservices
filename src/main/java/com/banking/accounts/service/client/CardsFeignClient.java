package com.banking.accounts.service.client;

import com.banking.accounts.constants.AccountConstants;
import com.banking.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = CardsFeignClient.CARDS_MICROSERVICE_NAME, fallback = CardsFallback.class)
public interface CardsFeignClient {
    String CARDS_MICROSERVICE_NAME = "cards";

    @GetMapping("/api/fetch")
    ResponseEntity<CardDto> fetch(@RequestHeader(name = AccountConstants.TRACE_ID, required = false) String traceId, @RequestParam String mobileNumber);
}
