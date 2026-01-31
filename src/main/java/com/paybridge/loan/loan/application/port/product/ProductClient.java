package com.paybridge.loan.loan.application.port.product;

import com.paybridge.loan.loan.domain.model.ProductTenor;

import java.util.UUID;

public interface ProductClient {
    ProductTenor getLoanTenor(UUID tenorId);
}

