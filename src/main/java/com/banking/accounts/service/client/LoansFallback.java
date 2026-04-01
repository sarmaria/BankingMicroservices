package com.banking.accounts.service.client;

import com.banking.accounts.dto.LoanDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<LoanDto> fetch(String traceId, String mobileNumber) {
        return null;
    }
}
