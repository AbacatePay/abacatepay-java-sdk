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
        // Creating a JSON object from the AbacatePayClientRequest object
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
        if (response == null) {
            return null;
        }
        // Converting the response string into a JSONObject
        JSONObject clientResponseJson = new JSONObject(response);

        // Verifying if the 'metadata' field exists in the response
        if (!clientResponseJson.has("metadata")) {
            throw new IllegalArgumentException("Missing metadata in response");
        }

        // Constructing MetaData with default values
        MetaData metaData = MetaData.builder()
                .name(clientResponseJson.optJSONObject("metadata") != null
                        ? clientResponseJson.getJSONObject("metadata").optString("name", "Unknown")
                        : "Unknown")
                .cellphone(clientResponseJson.optJSONObject("metadata") != null
                        ? clientResponseJson.getJSONObject("metadata").optString("cellphone", "Unknown")
                        : "Unknown")
                .email(clientResponseJson.optJSONObject("metadata") != null
                        ? clientResponseJson.getJSONObject("metadata").optString("email", "Unknown")
                        : "Unknown")
                .taxId(clientResponseJson.optJSONObject("metadata") != null
                        ? clientResponseJson.getJSONObject("metadata").optString("taxId", "Unknown")
                        : "Unknown")
                .build();

        // Returning the AbacatePayClientResponse object
        return AbacatePayClientResponse.builder()
                .id(clientResponseJson.optString("id", "Unknown"))
                .metaData(metaData)
                .build();
    }


    @Override
    public List<AbacatePayClientResponse> abacatePayClientResponseFromJsonArrayToObject(String response) {
        // Converting JSON array string to a List of AbacatePayClientResponse
        JSONArray jsonArray = new JSONArray(response);
        List<AbacatePayClientResponse> abacatePayClientResponses = new ArrayList<>();

        // Verifying each object in the array
        jsonArray.forEach(object -> {
            JSONObject jsonObject = new JSONObject(object.toString());

            if (!jsonObject.has("metadata")) {
                throw new IllegalArgumentException("Missing metadata in one of the client responses");
            }

            // Extracting metadata from JSON response
            MetaData metaData = MetaData.builder()
                    .name(jsonObject.getJSONObject("metadata").optString("name", "Unknown"))
                    .cellphone(jsonObject.getJSONObject("metadata").optString("cellphone", "Unknown"))
                    .email(jsonObject.getJSONObject("metadata").optString("email", "Unknown"))
                    .taxId(jsonObject.getJSONObject("metadata").optString("taxId", "Unknown"))
                    .build();

            // Adding each client response to the list
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

        // Adding billing methods as JSON array
        JSONArray methodsArray = new JSONArray();
        for (BillingMethod method : billingData.getMethods()) {
            methodsArray.put(method.toString());
        }
        billingDataJson.put("methods", methodsArray);

        // Adding a list of products (CreateBillingProduct) to the JSON
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
        if (response == null) {
            return null;
        }

        JSONObject billingResponseJson = new JSONObject(response);

        JSONArray methodsJsonArray = billingResponseJson.optJSONArray("methods");
        JSONArray productsJsonArray = billingResponseJson.optJSONArray("products");
        JSONObject customerJson = billingResponseJson.optJSONObject("customer");

        List<BillingMethod> methodList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        if (methodsJsonArray != null) {
            methodsJsonArray.forEach(method -> methodList.add(BillingMethod.valueOf(method.toString())));
        }

        if (productsJsonArray != null) {
            productsJsonArray.forEach(product -> {
                JSONObject productJson = new JSONObject(product);
                Product productObj = Product.builder()
                        .Id(productJson.optString("id"))
                        .externalId(productJson.optString("externalId"))
                        .quantity(productJson.optInt("quantity"))
                        .build();
                productList.add(productObj);
            });
        }
        Optional<AbacatePayClientResponse> optionalAbacatePayClientResponse =
                customerJson != null ? Optional.ofNullable(abacatePayClientResponseFromJsonToObject(customerJson.toString())) : Optional.empty();

        // Constructing the Billing object from the parsed JSON data
        Billing billingResponse = Billing.builder()
                .id(billingResponseJson.optString("id"))
                .url(billingResponseJson.optString("url"))
                .amount(billingResponseJson.getBigDecimal(("amount")))
                .status(BillingStatus.valueOf(billingResponseJson.optString("status", "PENDING")))
                .devMode(billingResponseJson.optBoolean("devMode"))
                .methods(methodList)
                .products(productList)
                .frequency(BillingKind.valueOf(billingResponseJson.optString("frequency", "MONTHLY")))
                .nextBilling(
                        !billingResponseJson.optString("nextBilling").isEmpty()
                                ? LocalDateTime.parse(billingResponseJson.optString("nextBilling"))
                                : null
                )
                .customer(optionalAbacatePayClientResponse.orElse(null))
                .build();

        return billingResponse;
    }


    @Override
    public List<Billing> listingBillingResponseFromJsonToObject(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }

        // Parsing the JSON array from the response string
        JSONArray billingResponseJsonArray = new JSONArray(string);
        List<Billing> billingResponseList = new ArrayList<>();

        // Iterating through the array and converting each object to a Billing object
        for (int i = 0; i < billingResponseJsonArray.length(); i++) {

            JSONObject billingResponseJson = billingResponseJsonArray.getJSONObject(i);
            Billing billingResponse = createBillingResponseFromJsonToObject(billingResponseJson.toString());
            billingResponseList.add(billingResponse);
        }

        return billingResponseList;
    }

}
