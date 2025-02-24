package com.abacatepay;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.clients.factories.AbacatePayClientFactory;
import com.abacatepay.model.IAbacatePay;
import com.abacatepay.services.IAbacatePayBilling;
import com.abacatepay.services.IAbacatePayCustomer;
import com.abacatepay.services.impl.AbacatePayBilling;
import com.abacatepay.services.impl.AbacatePayCustomer;

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
        return new AbacatePayBilling(client);
    }

    @Override
    public IAbacatePayCustomer customer() {
        return new AbacatePayCustomer(client);
    }
}
