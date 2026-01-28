package com.paybridge.loan.loan.api.controller;

import com.paybridge.loan.api.response.ApiResponse;
import com.paybridge.loan.loan.api.dto.request.CreateLoanApplicationRequest;
import com.paybridge.loan.loan.api.dto.response.LoanApplicationResponse;
import com.paybridge.loan.loan.application.service.LoanApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan-applications")
public class LoanApplicationController {
    private final LoanApplicationService service;

    public LoanApplicationController(LoanApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LoanApplicationResponse>> apply(
            @RequestBody @Valid CreateLoanApplicationRequest request
    ) {
        var app = service.apply(request.toCommand());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Loan application submitted",
                        LoanApplicationResponse.from(app)
                ));

    }

}
