package com.paybridge.loan.loan.api.dto.response;

import com.paybridge.loan.loan.domain.model.LoanApplication;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record LoanApplicationDetailResponse(
        UUID id,
        String status,
        BigDecimal requestedAmount,
        Instant submittedAt,
        String productName
) {
    public static LoanApplicationDetailResponse from(LoanApplication app) {
        return new LoanApplicationDetailResponse(
                app.getId(),
                app.getStatus().name(),
                app.getRequestedAmount(),
                app.getSubmittedAt(),
                "Loan Basic (Hardcoded)"
        );
    }
}
