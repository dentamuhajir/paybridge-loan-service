package com.paybridge.loan.loan.infrastructure.client.product.dto;

public record ProductApiResponse<T>(
    boolean success,
    String message,
    T data
){}
