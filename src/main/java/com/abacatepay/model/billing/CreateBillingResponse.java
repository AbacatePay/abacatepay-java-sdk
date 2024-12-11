package com.abacatepay.model.billing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBillingResponse {
    private String error;
    private Billing data;

    public CreateBillingResponse(String error) {
        this.error = error;
    }
}
