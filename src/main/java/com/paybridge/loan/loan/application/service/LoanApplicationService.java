package com.paybridge.loan.loan.application.service;

import com.paybridge.loan.loan.api.dto.response.ApproveLoanApplicationResponse;
import com.paybridge.loan.loan.application.command.CreateLoanApplicationCommand;
import com.paybridge.loan.loan.domain.exception.InvalidLoanApplicationException;
import com.paybridge.loan.loan.domain.model.LoanApplication;
import com.paybridge.loan.loan.infrastructure.persistence.LoanApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoanApplicationService {
    private final LoanApplicationRepository repository;

    public LoanApplicationService(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public LoanApplication apply(CreateLoanApplicationCommand command) {

        LoanApplication loanApplication = LoanApplication.submit(
                command.userId(),
                command.productId(),
                command.loanProductId(),
                command.loanTenorId(),
                command.interestRate(),
                command.adminFee(),
                command.requestedAmount()
        );

        return repository.save(loanApplication);
    }

    @Transactional
    public LoanApplication approve(UUID loanId) {
        LoanApplication loanApplication = repository.findById(loanId)
                .orElseThrow(() ->
                        new InvalidLoanApplicationException("Loan application not found")
                );
        loanApplication.approve();
        return repository.save(loanApplication);
    }

}
