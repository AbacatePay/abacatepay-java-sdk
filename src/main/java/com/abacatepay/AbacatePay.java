package com.abacatepay;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.clients.factories.AbacatePayClientFactory;
import com.abacatepay.model.IAbacatePay;
import com.abacatepay.model.IAbacatePayBilling;
import com.abacatepay.model.IAbacatePayWithdraw;
import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import com.abacatepay.model.withdraw.payloads.request.CreateWithdrawRequest;
import com.abacatepay.model.withdraw.payloads.response.CreateWithdrawResponse;
import com.abacatepay.model.withdraw.payloads.response.ListWithdrawResponse;
import com.abacatepay.model.withdraw.payloads.response.WithdrawResponse;
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

        // TODO: Pegar a versão do SDK dinamicamente
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

    @Override
    public IAbacatePayWithdraw withdraw() {
        class AbacatePayWithdraw implements IAbacatePayWithdraw {
            @Override
            public CreateWithdrawResponse createWithdraw(CreateWithdrawRequest body) {
                try {
                    return client.createWithdraw(body);
                } catch (IllegalArgumentException | FeignException e) {
                    return new CreateWithdrawResponse(e.getMessage());
                }
            }

            @Override
            public ListWithdrawResponse listWithdraw() {
                try {
                    return client.listWithdraw();
                } catch (IllegalArgumentException | FeignException e) {
                    return new ListWithdrawResponse(e.getMessage());
                }
            }

            @Override
            public WithdrawResponse getWithdraw(String externalId) {
                try {
                    return client.getWithdraw(externalId);
                } catch (IllegalArgumentException | FeignException e) {
                    return new WithdrawResponse(e.getMessage());
                }
            }
        }

        return new AbacatePayWithdraw();
    }
}
