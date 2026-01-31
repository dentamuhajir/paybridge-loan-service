package com.paybridge.loan.loan.api.controller;

import com.paybridge.loan.api.response.ApiResponse;
import com.paybridge.loan.loan.api.dto.request.CreateLoanApplicationRequest;
import com.paybridge.loan.loan.api.dto.response.LoanApplicationResponse;
import com.paybridge.loan.loan.application.port.product.ProductClient;
import com.paybridge.loan.loan.application.service.LoanApplicationService;
import com.paybridge.loan.loan.domain.model.LoanApplication;
import com.paybridge.loan.loan.domain.model.ProductTenor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/loan-applications")
public class LoanApplicationController {
    private final LoanApplicationService service;
    private final ProductClient productClient;

    public LoanApplicationController(LoanApplicationService service, ProductClient productClient) {
        this.service = service;
        this.productClient = productClient;
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
    @PostMapping("{id}/approve")
    public ResponseEntity<ApiResponse<Void>> approve(
            @PathVariable UUID id
    ) {

        LoanApplication app = service.approve(id);
        ProductTenor productTenor = productClient.getLoanTenor(app.getLoanTenorId());

        // create loan apps

        return ResponseEntity.ok(
                ApiResponse.success("Loan application approved", null)
        );

    }

}
