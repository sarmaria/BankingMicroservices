package com.banking.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="Account")
public record AccountDto(
        @Schema(name="Account number")
        Long accountNumber,

        @Schema(name="Customer Id of the customer")
        Long customerId,

        @Schema(name="Account Type", example="SAVINGS")
        String accountType,

        @Schema(name="Branch Address")
        String branchAddress ) {
}
