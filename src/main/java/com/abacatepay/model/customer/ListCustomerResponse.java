package com.abacatepay.model.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListCustomerResponse {
    private String error;
    private List<Customer> data;

    public ListCustomerResponse(String error) {
        this.error = error;
    }
}
