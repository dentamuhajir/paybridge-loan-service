package com.paybridge.loan.loan.api.dto.request;

import com.paybridge.loan.loan.application.command.CreateLoanApplicationCommand;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;


public record CreateLoanApplicationRequest(
        @NotNull UUID userId,
        @NotNull UUID productId,
        @NotNull UUID loanProductId,
        @NotNull UUID loanTenorId,
        @NotNull BigDecimal requestedAmount
) {
    public CreateLoanApplicationCommand toCommand() {
        return new CreateLoanApplicationCommand(
                userId, productId, loanProductId, loanTenorId, requestedAmount
        );
    }
}