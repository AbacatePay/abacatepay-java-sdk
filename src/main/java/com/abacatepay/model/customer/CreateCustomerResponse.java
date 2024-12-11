package com.abacatepay.model.customer;

import lombok.Data;

@Data
public class CreateCustomerResponse {
    private CustomerMetadata data;
    private String error;

    public CreateCustomerResponse(String error) {
        this.error = error;
    }
}
