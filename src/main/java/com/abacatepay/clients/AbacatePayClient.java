package com.abacatepay.clients;

import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import com.abacatepay.model.pixqrcode.CheckPixQRCodeResponse;
import com.abacatepay.model.pixqrcode.CreatePixQRCodeData;
import com.abacatepay.model.pixqrcode.CreatePixQRCodeResponse;
import feign.RequestLine;

public interface AbacatePayClient {

    @RequestLine("GET /billing/list")
    ListBillingResponse list();

    @RequestLine("POST /billing/create")
    CreateBillingResponse create(CreateBillingData body);

    @RequestLine("GET /pixQrCode/check")
    CheckPixQRCodeResponse check(String id);

    @RequestLine("POST /pixQrCode/create")
    CreatePixQRCodeResponse create(CreatePixQRCodeData body);
}
