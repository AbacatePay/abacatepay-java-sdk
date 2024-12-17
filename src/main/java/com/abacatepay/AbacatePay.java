package com.abacatepay;

import com.abacatepay.Models.Client.AbacatePayClient;
import com.abacatepay.Models.Client.factories.AbacatePayClientFactory;
import com.abacatepay.Models.IAbacatePay;
import com.abacatepay.Models.IAbacatePayBilling;
import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Models.Billing.ListBillingResponse;
import feign.FeignException;
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

        class AbacatePayBilling implements IAbacatePayBilling {

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

        return new AbacatePayBilling();
    }
}
