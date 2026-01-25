package com.paybridge.loan.loan.application.command;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateLoanApplicationCommand (
    UUID userId,
    UUID productId,
    UUID loanProductId,
    UUID loanTenorId,
    BigDecimal requestedAmount
) {}
