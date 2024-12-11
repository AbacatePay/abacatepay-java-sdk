package com.abacatepay.clients;

import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import feign.RequestLine;

public interface AbacatePayClient {

    @RequestLine("GET /billing/list")
    ListBillingResponse listBillings();

    @RequestLine("POST /billing/create")
    CreateBillingResponse createBilling(CreateBillingData body);
}
