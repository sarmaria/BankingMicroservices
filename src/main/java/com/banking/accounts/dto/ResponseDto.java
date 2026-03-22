package com.banking.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(name="Response")
public class ResponseDto {
    private String message;
    private HttpStatus status;
}
