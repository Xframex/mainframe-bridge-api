package com.zenithbank.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

/**
 * Data Transfer Object for Account operations.
 * Separates API contract from Database Schema.
 */
@Data
public class AccountDTO {

    @NotBlank(message = "Account ID is mandatory")
    @Size(max = 10)
    private String accountId;

    @NotBlank(message = "Customer Name is mandatory")
    private String customerName;

    @NotBlank(message = "Account Type is mandatory")
    private String accountType;

    @PositiveOrZero(message = "Balance cannot be negative")
    private BigDecimal balance;

    private String status;
}
