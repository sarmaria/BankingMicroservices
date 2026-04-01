package com.banking.accounts.service.client;

import com.banking.accounts.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardDto> fetch(String traceId, String mobileNumber) {
        return null;
    }
}
