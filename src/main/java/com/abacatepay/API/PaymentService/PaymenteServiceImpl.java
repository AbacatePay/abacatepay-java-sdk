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

@Service
public class PaymenteServiceImpl implements PaymentService {
    private final OkHttpClient client = new OkHttpClient();
    private final JsonUtilService jsonUtilService;
    public PaymenteServiceImpl(JsonUtilService jsonUtilService) {
        this.jsonUtilService = jsonUtilService;
    }

    @Override
    public Billing createBilling(CreateBillingData billingData, AbacatePayConfig config){
        try{
            JSONObject jsonObject = jsonUtilService.createBillingDataJson(billingData);
            RequestBody requestBody = RequestBody.create(jsonObject.toString(), MediaType.get("application/json"));
            Request req = new Request.Builder()
                    .url(config.getBaseUrl() + "/customer/create")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + config.getApiKey())
                    .addHeader("content-type", "application/json")
                    .build();
            Response res = client.newCall(req).execute();
            if (!res.isSuccessful()){
                throw new RuntimeException("Unauthorized API token. API_TOKEN: "+config.getApiKey());
            }
            Billing billingResponse = jsonUtilService.createBillingResponseFromJsonToObject(res.toString());
            return billingResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CreateBillingResponse> listingBillings(AbacatePayConfig config) {
        return List.of();
    }
}
