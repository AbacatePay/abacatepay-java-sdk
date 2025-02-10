package com.abacatepay.model.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCustomerResponse {
    private CustomerMetadata data;
    private String error;

    public CreateCustomerResponse(String error) {
        this.error = error;
    }
}
