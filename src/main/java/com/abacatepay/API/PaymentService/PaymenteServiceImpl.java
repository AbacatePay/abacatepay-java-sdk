package com.abacatepay.API.PaymentService;

import com.abacatepay.Models.Billing.Billing;
import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;
import com.abacatepay.Utils.JsonUtils.JsonUtilService;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymenteServiceImpl implements PaymentService {
    private final OkHttpClient client = new OkHttpClient();
    private final JsonUtilService jsonUtilService;

    public PaymenteServiceImpl(JsonUtilService jsonUtilService) {
        this.jsonUtilService = jsonUtilService;
    }

    @Override
    public Optional<Billing> createBilling(CreateBillingData billingData, AbacatePayConfig config) {
        try {

            JSONObject jsonObject = jsonUtilService.createBillingDataJson(billingData);
            RequestBody requestBody = RequestBody.create(jsonObject.toString(), MediaType.get("application/json"));
            Request req = new Request.Builder()
                    .url(config.getBaseUrl() + "/billing/create")
                    .post(requestBody)
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + config.getApiKey())
                    .addHeader("content-type", "application/json")
                    .build();


            Response res = client.newCall(req).execute();

            if (!res.isSuccessful()) {
                throw new RuntimeException("Unauthorized API token. API_TOKEN: " + config.getApiKey());
            }

            assert res.body() != null;
            String responseBody = res.body().string();
            return Optional.ofNullable(jsonUtilService.createBillingResponseFromJsonToObject(responseBody));

        } catch (Exception e) {
            throw new RuntimeException("Error during API request: " + e.getMessage());
        }
    }

    @Override
    public Optional<List<Billing>> listingBillings(AbacatePayConfig config) {
        try {
            Request req = new Request.Builder()
                    .url(config.getBaseUrl() + "/billing/list")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + config.getApiKey())
                    .addHeader("content-type", "application/json")
                    .build();
            Response res = client.newCall(req).execute();

            if (!res.isSuccessful()) {
                throw new RuntimeException("Unauthorized API token. API_TOKEN: " + config.getApiKey());
            }

            assert res.body() != null;
            String responseBody = res.body().string();
            return Optional.ofNullable(jsonUtilService.listingBillingResponseFromJsonToObject(responseBody));

        } catch (Exception e) {
            throw new RuntimeException("Error during API request: " + e.getMessage());
        }
    }
}
