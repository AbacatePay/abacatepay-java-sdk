package com.abacatepay.clients;

import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import com.abacatepay.model.withdraw.payloads.request.CreateWithdrawRequest;
import com.abacatepay.model.withdraw.payloads.response.CreateWithdrawResponse;
import com.abacatepay.model.withdraw.payloads.response.ListWithdrawResponse;
import com.abacatepay.model.withdraw.payloads.response.WithdrawResponse;
import feign.Param;
import feign.RequestLine;

public interface AbacatePayClient {

    @RequestLine("GET /billing/list")
    ListBillingResponse list();

    @RequestLine("POST /billing/create")
    CreateBillingResponse create(CreateBillingData body);

    @RequestLine("POST /withdraw/create")
    CreateWithdrawResponse createWithdraw(CreateWithdrawRequest body);

    @RequestLine("GET /withdraw/list")
    ListWithdrawResponse listWithdraw();

    @RequestLine("GET /withdraw/get?externalId={externalId}")
    WithdrawResponse getWithdraw(@Param("externalId") String externalId);
}
