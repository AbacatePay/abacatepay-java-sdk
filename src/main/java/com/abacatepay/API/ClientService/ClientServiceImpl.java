package com.abacatepay.API.ClientService;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;
import com.abacatepay.Utils.Exceptions.ClientExeption;
import com.abacatepay.Utils.JsonUtils.JsonUtilService;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final JsonUtilService jsonUtilService;
    private final OkHttpClient client = new OkHttpClient();

    public ClientServiceImpl(JsonUtilService jsonUtilService) {
        this.jsonUtilService = jsonUtilService;
    }

    @Override
    public AbacatePayClientResponse createNewClient(
            AbacatePayClientRequest abacatePayClientRequest,
            AbacatePayConfig config
    ) {
        try {
            // Creating JSON from AbacatePayClient to OkHttpRequest
            JSONObject AbacatePayClientJson = jsonUtilService.createAbacatePayClientJson(abacatePayClientRequest);
            RequestBody requestBody = RequestBody.create(String.valueOf(AbacatePayClientJson), MediaType.get("application/json; charset=utf-8"));

            // Creating a request
            Request req = new Request.Builder()
                    .url(config.getBASE_URL() + "/customer/create")
                    .post(requestBody)
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + config.getAPI_KEY())
                    .addHeader("content-type", "application/json")
                    .build();

            // Making a Request
            Response res = client.newCall(req).execute();

            // If request has a 401 throw a new ClientException
            if (!res.isSuccessful()) {
                throw new ClientExeption("Unauthorized API Token.");
            }

            // If request was successful, return AbacatePayClient Object
            AbacatePayClientResponse abacatePayClientResponse = jsonUtilService.abacatePayClientResponseFromJsonToObject(res.body().string());
            return abacatePayClientResponse;

        } catch (ClientExeption e) {
            throw new ClientExeption(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AbacatePayClientResponse> listingClients(AbacatePayConfig config) {
        try {
            Request req = new Request.Builder()
                    .url(config.getBASE_URL() + "/customer/create")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + config.getAPI_KEY())
                    .addHeader("content-type", "application/json")
                    .build();

            try (Response res = client.newCall(req).execute()) {
                if (!res.isSuccessful()) {
                    throw new ClientExeption("Unauthorized API Token");
                }

                if (res.body() == null) {
                    throw new RuntimeException("Response body is null");
                }

                String responseBody = res.body().string();
                return jsonUtilService.abacatePayClientResponseFromJsonArrayToObject(responseBody);

            } catch (Exception e) {
                throw new RuntimeException("Error during the API request: " + e.getMessage(), e);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while making the request: " + e.getMessage(), e);
        }
    }



}

