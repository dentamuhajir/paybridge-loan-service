package com.paybridge.loan.loan.api.dto.response;

import com.paybridge.loan.loan.domain.model.LoanApplication;
import java.util.UUID;

public record LoanApplicationResponse(
        UUID id,
        String status
) {
    public static LoanApplicationResponse from(LoanApplication app) {
        return new LoanApplicationResponse(
                app.getId(),
                app.getStatus().name()
        );
    }
}
