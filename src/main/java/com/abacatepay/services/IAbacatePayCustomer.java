package com.abacatepay.services;

import com.abacatepay.model.customer.CreateCustomerResponse;
import com.abacatepay.model.customer.CustomerMetadata;
import com.abacatepay.model.customer.ListCustomerResponse;

public interface IAbacatePayCustomer {
    CreateCustomerResponse create(CustomerMetadata data);
    ListCustomerResponse list();
}
