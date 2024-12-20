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
        if (!clientResponseJson.has("data")) {
            throw new IllegalArgumentException("Missing data in response");
        }

        // Constructing MetaData with default values
        MetaData metaData = MetaData.builder()
                .name(clientResponseJson.optJSONObject("data").optJSONObject("metadata") != null
                        ? clientResponseJson.optJSONObject("data").optJSONObject("metadata").optString("name", "Unknown")
                        : "Unknown")
                .cellphone(clientResponseJson.optJSONObject("data").optJSONObject("metadata") != null
                        ? clientResponseJson.optJSONObject("data").optJSONObject("metadata").optString("cellphone", "Unknown")
                        : "Unknown")
                .email(clientResponseJson.optJSONObject("data").optJSONObject("metadata") != null
                        ? clientResponseJson.optJSONObject("data").optJSONObject("metadata").optString("email", "Unknown")
                        : "Unknown")
                .taxId(clientResponseJson.optJSONObject("data").optJSONObject("metadata") != null
                        ? clientResponseJson.optJSONObject("data").optJSONObject("metadata").optString("taxId", "Unknown")
                        : "Unknown")
                .build();

        // Returning the AbacatePayClientResponse object
        return AbacatePayClientResponse.builder()
                .id(clientResponseJson.optJSONObject("data").optString("id", "Unknown"))
                .metaData(metaData)
                .build();
    }


    @Override
    public List<AbacatePayClientResponse> abacatePayClientResponseFromJsonArrayToObject(String response) {
        JSONObject jsonObject = new JSONObject(response);
        List<AbacatePayClientResponse> abacatePayClientResponses = new ArrayList<>();
        if (!jsonObject.has("data")){
            throw new RuntimeException("Data is missing in response");
        }
        for (int i = 0; i<jsonObject.optJSONArray("data").length(); i++){

                MetaData metaData = MetaData.builder()
                        .name(jsonObject.optJSONArray("data")
                                .optJSONObject(i)
                                .getJSONObject("metadata")
                                .optString("name", "Unknown"))
                        .cellphone(jsonObject.optJSONArray("data")
                                .optJSONObject(i)
                                .getJSONObject("metadata")
                                .optString("cellphone", "Unknown"))
                        .email(jsonObject.optJSONArray("data")
                                .optJSONObject(i)
                                .getJSONObject("metadata")
                                .optString("email", "Unknown"))
                        .taxId(jsonObject.optJSONArray("data")
                                .optJSONObject(i)
                                .getJSONObject("metadata")
                                .optString("taxId", "Unknown"))
                        .build();

                // Adding each client response to the list
                abacatePayClientResponses.add(
                        AbacatePayClientResponse.builder()
                                .id(jsonObject.optJSONArray("data")
                                        .optJSONObject(i)
                                        .optString("id", "Unknown"))
                                .metaData(metaData)
                                .build());

        }


        return abacatePayClientResponses;
    }

    @Override
    public JSONObject createBillingDataJson(CreateBillingData billingData) {
        JSONObject billingDataJson = new JSONObject();
        billingDataJson.put("frequency", billingData.getFrequency().toString());

        // Adding billing methods as JSON array
        JSONArray methodsArray = new JSONArray();
        for (int i = 0; i < billingData.getMethods().size(); i++) {
            methodsArray.put(billingData.getMethods().get(i));
        }
        billingDataJson.put("methods", methodsArray);

        // Adding a list of products (CreateBillingProduct) to the JSON
        JSONArray productsArray = new JSONArray();
        for (int i = 0; i < billingData.getProducts().size(); i++) {
            JSONObject productJson = new JSONObject();
            productJson.put("externalId", billingData.getProducts().get(i).getExternalId());
            productJson.put("name", billingData.getProducts().get(i).getName());
            productJson.put("description", billingData.getProducts().get(i).getDescription());
            productJson.put("quantity", billingData.getProducts().get(i).getQuantity());
            productJson.put("price", billingData.getProducts().get(i).getPrice());
            productsArray.put(productJson);
        }

        billingDataJson.put("products", productsArray);
        billingDataJson.put("returnUrl", billingData.getReturnUrl());
        billingDataJson.put("completionUrl", billingData.getCompletionUrl());
        billingDataJson.put("customerId", billingData.getCustomerId());

        // Converting the 'customer' object to a JSONObject
        JSONObject customerJson = new JSONObject();
        customerJson.put("name", billingData.getCustomer().getName());
        customerJson.put("cellphone", billingData.getCustomer().getCellphone());
        customerJson.put("email", billingData.getCustomer().getEmail());
        customerJson.put("taxId", billingData.getCustomer().getTaxId());

        billingDataJson.put("customer", customerJson);

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
