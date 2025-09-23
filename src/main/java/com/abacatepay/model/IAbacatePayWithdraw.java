package com.abacatepay.model;

import com.abacatepay.model.withdraw.payloads.request.CreateWithdrawRequest;
import com.abacatepay.model.withdraw.payloads.response.CreateWithdrawResponse;
import com.abacatepay.model.withdraw.payloads.response.ListWithdrawResponse;
import com.abacatepay.model.withdraw.payloads.response.WithdrawResponse;

public interface IAbacatePayWithdraw {
    CreateWithdrawResponse createWithdraw(CreateWithdrawRequest body);

    ListWithdrawResponse listWithdraw();

    WithdrawResponse getWithdraw(String externalId);
}
