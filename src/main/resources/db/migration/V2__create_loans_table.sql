CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE TABLE loans (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    loan_application_id UUID NOT NULL,
    user_id UUID NOT NULL,

    principal_amount NUMERIC(14, 2) NOT NULL,
    interest_type VARCHAR(20) NOT NULL,
    interest_rate NUMERIC(5, 2) NOT NULL,

    tenor_months INT NOT NULL,

    total_interest NUMERIC(14, 2) NOT NULL,
    total_payable NUMERIC(14, 2) NOT NULL,

    status VARCHAR(20) NOT NULL,

    disbursed_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_loans_loan_application
        FOREIGN KEY (loan_application_id)
        REFERENCES loan_applications (id),

    CONSTRAINT chk_interest_type
        CHECK (interest_type IN ('FLAT', 'ANNUITY')),

    CONSTRAINT chk_loan_status
        CHECK (status IN ('CREATED', 'ACTIVE', 'COMPLETED')),

    CONSTRAINT chk_positive_principal
        CHECK (principal_amount > 0),

    CONSTRAINT chk_positive_tenor
        CHECK (tenor_months > 0)
);
