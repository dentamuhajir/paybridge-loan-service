-- Required for UUID generation
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- ============================
-- Loan Applications
-- ============================
CREATE TABLE loan_applications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    user_id UUID NOT NULL,

    product_id UUID NOT NULL,
    loan_product_id UUID NOT NULL,
    loan_tenor_id UUID NOT NULL,

    requested_amount NUMERIC(14,2) NOT NULL,

    interest_rate NUMERIC(5,2),
    admin_fee NUMERIC(14,2),

    status VARCHAR(30) NOT NULL,

    submitted_at TIMESTAMP,
    approved_at TIMESTAMP,

    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_loan_applications_user_id
    ON loan_applications(user_id);

CREATE INDEX idx_loan_applications_status
    ON loan_applications(status);
