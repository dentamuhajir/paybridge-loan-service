package com.paybridge.loan.loan.infrastructure.client.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductTenorResponse(
        @JsonProperty("tenorId")
        UUID tenorId,

        @JsonProperty("tenorMonths")
        int tenorMonths,

        @JsonProperty("interestRate")
        BigDecimal interestRate,

        @JsonProperty("adminFee")
        BigDecimal adminFee,

        @JsonProperty("interestType")
        String interestType
) {
}
