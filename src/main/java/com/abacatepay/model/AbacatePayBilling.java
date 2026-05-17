package com.abacatepay.model;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import feign.FeignException;

public class AbacatePayBilling implements IAbacatePayBilling {

    private final AbacatePayClient client;

    public AbacatePayBilling(AbacatePayClient client) {
        this.client = client;
    }

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