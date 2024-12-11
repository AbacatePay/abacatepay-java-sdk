package com.abacatepay.model;

import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import com.abacatepay.model.customer.CreateCustomerResponse;
import com.abacatepay.model.customer.CustomerMetadata;
import com.abacatepay.model.customer.ListCustomerResponse;

public interface IAbacatePayBilling {
    CreateBillingResponse create(CreateBillingData data);
    ListBillingResponse list();
    CreateCustomerResponse createCustomer(CustomerMetadata data);
    ListCustomerResponse listCustomers();
}
