package com.abacatepay;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.clients.factories.AbacatePayClientFactory;
import com.abacatepay.model.IAbacatePay;
import com.abacatepay.model.IAbacatePayBilling;
import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import feign.FeignException;

public class AbacatePay implements IAbacatePay {

    private static final String API_BASE_URL = "https://api.abacatepay.com/v1";
    private final AbacatePayClient client;

    public AbacatePay(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key not provided");
        }

        //TODO: Pegar a versão do SDK dinamicamente
        String userAgent = "Java SDK (1.0.0)";
        this.client = AbacatePayClientFactory.create(API_BASE_URL, apiKey, userAgent);
    }

    @Override
    public IAbacatePayBilling billing() {

        class AbacatePayBilling implements IAbacatePayBilling {

            @Override
            public CreateBillingResponse create(CreateBillingData data) {
                try {
                    return client.createBilling(data);
                } catch (IllegalArgumentException | FeignException e) {
                    return new CreateBillingResponse(e.getMessage());
                }
            }

            @Override
            public ListBillingResponse list() {
                try {
                    return client.listBillings();
                } catch (IllegalArgumentException | FeignException e) {
                    return new ListBillingResponse(e.getMessage());
                }
            }
        }

        return new AbacatePayBilling();
    }
}
