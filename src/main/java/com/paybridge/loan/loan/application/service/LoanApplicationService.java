package com.paybridge.loan.loan.application.service;

import com.paybridge.loan.loan.application.command.CreateLoanApplicationCommand;
import com.paybridge.loan.loan.domain.model.LoanApplication;
import com.paybridge.loan.loan.infrastructure.persistence.LoanApplicationRepository;
import org.springframework.stereotype.Service;
@Service
public class LoanApplicationService {
    private final LoanApplicationRepository repository;

    public LoanApplicationService(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    public LoanApplication apply(CreateLoanApplicationCommand command) {

        LoanApplication loanApplication = LoanApplication.submit(
                command.userId(),
                command.productId(),
                command.loanProductId(),
                command.loanTenorId(),
                command.requestedAmount()
        );

        return repository.save(loanApplication);
    }

}
