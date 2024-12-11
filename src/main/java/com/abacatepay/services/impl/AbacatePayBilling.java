package com.abacatepay.services.impl;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import com.abacatepay.services.IAbacatePayBilling;
import feign.FeignException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbacatePayBilling implements IAbacatePayBilling {

    private final AbacatePayClient client;

    @Override
    public CreateBillingResponse create(CreateBillingData data) {
        try {
            return client.create(data);
        } catch (IllegalArgumentException | FeignException e) {
            return new CreateBillingResponse(e.getMessage());
        }
    }

    @Override
    public ListBillingResponse list() {
        try {
            return client.list();
        } catch (IllegalArgumentException | FeignException e) {
            return new ListBillingResponse(e.getMessage());
        }
    }
}
