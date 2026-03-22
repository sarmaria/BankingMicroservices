package com.banking.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(name="Customer", description = "Customer and Account Details")
public record CustomerDto(
        @Schema(name="Name of the customer", example = "John Doe")
        @NotBlank(message = "Name is mandatory")
        String name,

        @Schema(name="Email of the customer", example = "johndoe@mail.com")
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Invalid email id")
        String email,

        @Schema(name="Mobile Number of the customer", example = "1234523456")
        @NotBlank(message = "Mobile number is mandatory")
        @Pattern(regexp = "^\\d{10,12}$", message="Mobile number must have 10 digits.")
        String mobileNumber,

        @Schema(name="Account Details of the customer")
        AccountDto accountDto) {
}
