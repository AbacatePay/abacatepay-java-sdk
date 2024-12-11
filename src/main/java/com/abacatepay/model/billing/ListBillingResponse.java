package com.abacatepay.model.billing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListBillingResponse {
    private String error;
    private List<Billing> data;

    public ListBillingResponse(String error) {
        this.error = error;
    }
}
