ALTER TABLE loans DROP CONSTRAINT chk_loan_status;

ALTER TABLE loans ADD CONSTRAINT chk_loan_status
CHECK (
    status IN (
        'CREATED',
        'DISBURSEMENT_PENDING',
        'ACTIVE',
        'FAILED'
    )
);