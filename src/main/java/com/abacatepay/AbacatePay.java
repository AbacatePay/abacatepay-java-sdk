package com.abacatepay;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.clients.factories.AbacatePayClientFactory;
import com.abacatepay.model.IAbacatePay;
import com.abacatepay.services.IAbacatePayBilling;
import com.abacatepay.services.IAbacatePayCustomer;
import com.abacatepay.services.impl.AbacatePayBilling;
import com.abacatepay.services.impl.AbacatePayCustomer;
import feign.RequestInterceptor;

public class AbacatePay implements IAbacatePay {

    private static final String API_BASE_URL = "https://api.abacatepay.com/v1";

    private final AbacatePayClient client;
    private final String apiKey;
    private final String userAgent;

    public AbacatePay(String apiKey) {
        this.apiKey = apiKey;
        this.client = AbacatePayClientFactory.create(API_BASE_URL, requestInterceptor());

        //TODO: Pegar a versão do SDK dinamicamente
        this.userAgent = "Java SDK (1.0.0)";
    }

    private RequestInterceptor requestInterceptor() {
        return template -> {
            if (apiKey == null || apiKey.isEmpty()) {
                throw new IllegalArgumentException("API key not provided");
            }

            template.header("Authorization", "Bearer " + apiKey);
            template.header("Content-Type", "application/json");
            template.header("User-Agent", userAgent);
        };
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
