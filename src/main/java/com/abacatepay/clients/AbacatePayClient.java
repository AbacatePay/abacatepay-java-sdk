package com.abacatepay.clients;

import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import com.abacatepay.model.customer.CreateCustomerResponse;
import com.abacatepay.model.customer.CustomerMetadata;
import com.abacatepay.model.customer.ListCustomerResponse;
import feign.RequestLine;

public interface AbacatePayClient {

    @RequestLine("GET /billing/list")
    ListBillingResponse listBillings();

    @RequestLine("POST /billing/create")
    CreateBillingResponse createBilling(CreateBillingData body);

    @RequestLine("POST /customer/create")
    CreateCustomerResponse createCustomer(CustomerMetadata body);

    @RequestLine("GET /customer/list")
    ListCustomerResponse listCustomers();
}
