package com.abacatepay.Models.Client;

import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Models.Billing.ListBillingResponse;
import feign.RequestLine;

public interface AbacatePayClient {

    @RequestLine("GET /billing/list")
    ListBillingResponse list();

    @RequestLine("POST /billing/create")
    CreateBillingResponse create(CreateBillingData body);
}
