package com.paybridge.loan.loan.domain.model;

import com.paybridge.loan.loan.domain.enums.LoanApplicationStatus;
import com.paybridge.loan.loan.domain.exception.InvalidLoanApplicationException;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "loan_applications")
@Data
public class LoanApplication {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "loan_product_id", nullable = false)
    private UUID loanProductId;

    @Column(name = "loan_tenor_id", nullable = false)
    private UUID loanTenorId;

    @Column(name = "interest_rate", precision = 5, scale = 2, nullable = false)
    private BigDecimal interestRate;

    @Column(name = "admin_fee", nullable = false)
    private BigDecimal adminFee;

    @Column(name = "requested_amount", nullable = false)
    private BigDecimal requestedAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanApplicationStatus status;

    @Column(name = "approved_at")
    private Instant approvedAt;

    @Column(name = "submitted_at")
    private Instant submittedAt;

    protected LoanApplication() {}

    public static LoanApplication submit(
            UUID userId,
            UUID productId,
            UUID loanProductId,
            UUID loanTenorId,
            BigDecimal interestRate,
            BigDecimal adminFee,
            BigDecimal requestedAmount
    ) {

        if(requestedAmount == null | requestedAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidLoanApplicationException(
                    "Requested amount must be greater than zero"
            );
        }

        LoanApplication app = new LoanApplication();
        app.id = UUID.randomUUID();
        app.userId = userId;
        app.productId = productId;
        app.loanProductId = loanProductId;
        app.loanTenorId = loanTenorId;
        app.interestRate = interestRate;
        app.adminFee = adminFee;
        app.requestedAmount = requestedAmount;
        app.status = LoanApplicationStatus.SUBMITTED;
        app.submittedAt = Instant.now();
        return app;
    }

    public void approve() {
        if(this.status != LoanApplicationStatus.SUBMITTED) {
            throw new InvalidLoanApplicationException(
                    "Only SUBMITTED application can be approved"
            );
        }

        this.status = LoanApplicationStatus.APPROVED;
        this.approvedAt = Instant.now();
    }

    public UUID getId() { return id; }
    public LoanApplicationStatus getStatus() { return status; }
}
