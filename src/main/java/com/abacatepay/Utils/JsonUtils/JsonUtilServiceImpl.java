package com.abacatepay.Utils.JsonUtils;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Models.AbacatePayClient.MetaData;
import com.abacatepay.Models.Billing.*;
import com.abacatepay.Models.ProductModel.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JsonUtilServiceImpl implements JsonUtilService {

    // REQUESTS
    @Override
    public JSONObject createAbacatePayClientJson(AbacatePayClientRequest request) {
        JSONObject clientRequestJson = new JSONObject();
        clientRequestJson.put("name", request.getName());
        clientRequestJson.put("cellphone", request.getCellphone());
        clientRequestJson.put("email", request.getEmail());
        clientRequestJson.put("taxId", request.getTaxId());
        return clientRequestJson;
    }

    // RESPONSES
    @Override
    public AbacatePayClientResponse abacatePayClientResponseFromJsonToObject(String response) {
        JSONObject clientResponseJson = new JSONObject(response);

        // Verificando se o campo 'metadata' existe no JSON
        if (!clientResponseJson.has("metadata")) {
            throw new IllegalArgumentException("Missing metadata in response");
        }

        MetaData metaData = MetaData.builder()
                .name(clientResponseJson.getJSONObject("metadata").optString("name", "Unknown"))
                .cellphone(clientResponseJson.getJSONObject("metadata").optString("cellphone", "Unknown"))
                .email(clientResponseJson.getJSONObject("metadata").optString("email", "Unknown"))
                .taxId(clientResponseJson.getJSONObject("metadata").optString("taxId", "Unknown"))
                .build();

        return AbacatePayClientResponse.builder()
                .id(clientResponseJson.optString("id", "Unknown"))
                .metaData(metaData)
                .build();
    }

    @Override
    public List<AbacatePayClientResponse> abacatePayClientResponseFromJsonArrayToObject(String response) {
        JSONArray jsonArray = new JSONArray(response);
        List<AbacatePayClientResponse> abacatePayClientResponses = new ArrayList<>();

        // Verifying each object in array
        jsonArray.forEach(object -> {
            JSONObject jsonObject = new JSONObject(object.toString());

            if (!jsonObject.has("metadata")) {
                throw new IllegalArgumentException("Missing metadata in one of the client responses");
            }

            MetaData metaData = MetaData.builder()
                    .name(jsonObject.getJSONObject("metadata").optString("name", "Unknown"))
                    .cellphone(jsonObject.getJSONObject("metadata").optString("cellphone", "Unknown"))
                    .email(jsonObject.getJSONObject("metadata").optString("email", "Unknown"))
                    .taxId(jsonObject.getJSONObject("metadata").optString("taxId", "Unknown"))
                    .build();

            abacatePayClientResponses.add(
                    AbacatePayClientResponse.builder()
                            .id(jsonObject.optString("id", "Unknown"))
                            .metaData(metaData)
                            .build()
            );
        });

        return abacatePayClientResponses;
    }

    @Override
    public JSONObject createBillingDataJson(CreateBillingData billingData) {

        JSONObject billingDataJson = new JSONObject();

        billingDataJson.put("frequency", billingData.getFrequency().toString());

        JSONArray methodsArray = new JSONArray();
        for (BillingMethod method : billingData.getMethods()) {
            methodsArray.put(method.toString());
        }
        billingDataJson.put("methods", methodsArray);

        // Adicionando a lista de produtos (CreateBillingProduct)
        JSONArray productsArray = new JSONArray();
        for (CreateBillingProduct product : billingData.getProducts()) {
            JSONObject productJson = new JSONObject();
            productJson.put("externalId", product.getExternalId());
            productJson.put("name", product.getName());
            productJson.put("description", product.getDescription());
            productJson.put("quantity", product.getQuantity());
            productJson.put("price", product.getPrice());
            productsArray.put(productJson);
        }

        billingDataJson.put("products", productsArray);
        billingDataJson.put("returnUrl", billingData.getReturnUrl());
        billingDataJson.put("completionUrl", billingData.getCompletionUrl());

        return billingDataJson;

    }

    @Override
    public Billing createBillingResponseFromJsonToObject(String response) {
        JSONObject billingResponseJson = new JSONObject(response);
        JSONArray methodsJsonArray = new JSONArray(billingResponseJson.getJSONArray("methods"));
        JSONArray productsJsonArray = new JSONArray(billingResponseJson.getJSONArray("products"));
        JSONObject customerJson = new JSONObject(billingResponseJson.getJSONObject("customer"));
        List<BillingMethod> methodList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        methodsJsonArray.forEach(method-> {

            methodList.add(BillingMethod.valueOf(method.toString()));
        });
        productsJsonArray.forEach(product ->{
            JSONObject productJson = new JSONObject(product);
            Product productObj = Product.builder()
                    .Id(productJson.optString("id"))
                    .externalId(productJson.optString("externalId"))
                    .quantity(productJson.getInt("quantity"))
                    .build();
            productList.add(productObj);
        });

        Optional<AbacatePayClientResponse> optionalAbacatePayClientResponse =
                customerJson.isEmpty()
                        ? null
                        : Optional.ofNullable(
                                abacatePayClientResponseFromJsonToObject(customerJson.toString())
                );

        Billing billingResponse = Billing.builder()
                .id(billingResponseJson.optString("id"))
                .url(billingResponseJson.optString("url"))
                .amount(billingResponseJson.getBigDecimal("amount"))
                .status(BillingStatus.valueOf(billingResponseJson.optString("status")))
                .devMode(billingResponseJson.optBoolean("devMode"))
                .methods(methodList)
                .products(productList)
                .frequency(BillingKind.valueOf(billingResponseJson.optString("frequency")))
                .nextBilling(
                        !billingResponseJson.optString("nextBilling").isEmpty()
                                ? LocalDateTime.parse(billingResponseJson.optString("nextBilling"))
                                : null
                        )
                .customer(optionalAbacatePayClientResponse.get())
                .build();
        return billingResponse;
    }
}
