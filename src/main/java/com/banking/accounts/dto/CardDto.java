package com.banking.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record CardDto(
        @Schema(description = "Type of the card", example = "Credit Card")
        @NotBlank(message = "Card Type is mandatory")
        String cardType,

        @Schema(description = "Mobile number", example = "12344123422")
        @NotBlank(message = "Mobile number is mandatory")
        @Pattern(regexp = "^\\d{10,12}$", message = "Mobile number must have 10 digits")
        String mobileNumber,

        @Schema(description = "Unique Card Number", example = "12312312313")
        @NotBlank(message = "Card number is mandatory")
        String cardNumber,

        @Schema(description = "Total Limit of the card")
        @NotNull(message = "Total limit is mandatory")
        @Positive(message = "Total limit must be a positive value")
        int totalLimit,

        @Schema(description = "Amount used on the card")
        @NotNull(message = "Amount used is mandatory")
        @PositiveOrZero(message = "Amount used cannot be less than zero")
        int amountUsed,

        @Schema(description = "Available amount in the card")
        @NotNull(message = "Available amount is mandatory")
        @PositiveOrZero(message = "Available cannot be less than zero")
        int availableAmount) {
}
