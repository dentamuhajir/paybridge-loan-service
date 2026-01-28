package com.paybridge.loan.loan.domain.exception;

import com.paybridge.loan.shared.exception.BusinessException;

public class InvalidLoanApplicationException extends BusinessException {
    public InvalidLoanApplicationException(String message) {
        super(message);
    }
}
