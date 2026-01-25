package com.paybridge.loan.loan.domain.model;

import com.paybridge.loan.loan.domain.enums.LoanApplicationStatus;
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

    @Column(name = "requested_amount", nullable = false)
    private BigDecimal requestedAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanApplicationStatus status;

    @Column(name = "submitted_at")
    private Instant submittedAt;

    protected LoanApplication() {}

    public static LoanApplication submit(
            UUID userId,
            UUID productId,
            UUID loanProductId,
            UUID loanTenorId,
            BigDecimal requestedAmount
    ) {
        LoanApplication app = new LoanApplication();
        app.id = UUID.randomUUID();
        app.userId = userId;
        app.productId = productId;
        app.loanProductId = loanProductId;
        app.loanTenorId = loanTenorId;
        app.requestedAmount = requestedAmount;
        app.status = LoanApplicationStatus.SUBMITTED;
        app.submittedAt = Instant.now();
        return app;
    }

    public UUID getId() { return id; }
    public LoanApplicationStatus getStatus() { return status; }
}
