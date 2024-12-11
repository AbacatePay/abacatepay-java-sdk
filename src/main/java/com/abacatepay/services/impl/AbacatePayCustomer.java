package com.abacatepay.services.impl;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.model.customer.CreateCustomerResponse;
import com.abacatepay.model.customer.CustomerMetadata;
import com.abacatepay.model.customer.ListCustomerResponse;
import com.abacatepay.services.IAbacatePayCustomer;
import feign.FeignException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbacatePayCustomer implements IAbacatePayCustomer {

    private final AbacatePayClient client;

    @Override
    public CreateCustomerResponse create(CustomerMetadata data) {
        try {
            return client.createCustomer(data);
        } catch (IllegalArgumentException | FeignException e) {
            return new CreateCustomerResponse(e.getMessage());
        }
    }

    @Override
    public ListCustomerResponse list() {
        try {
            return client.listCustomers();
        } catch (IllegalArgumentException | FeignException e) {
            return new ListCustomerResponse(e.getMessage());
        }
    }
}
