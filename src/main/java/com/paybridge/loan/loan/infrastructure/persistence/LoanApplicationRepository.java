package com.paybridge.loan.loan.infrastructure.persistence;

import com.paybridge.loan.loan.domain.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, UUID> {
}
