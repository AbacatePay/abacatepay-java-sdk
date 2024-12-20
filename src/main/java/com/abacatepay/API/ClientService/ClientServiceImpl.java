package com.abacatepay.API.ClientService;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;
import com.abacatepay.Utils.Exceptions.ClientExeption;
import com.abacatepay.Utils.JsonUtils.JsonUtilService;
import okhttp3.*;
import org.json.JSONObject;
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
            //From object to JSON
            JSONObject abacatePayClientJson = jsonUtilService.createAbacatePayClientJson(abacatePayClientRequest);
            RequestBody requestBody = RequestBody.create(abacatePayClientJson.toString(), MediaType.get("application/json; charset=utf-8"));

            // POST to create client
            Request req = new Request.Builder()
                    .url(config.getBaseUrl() + "/customer/create")
                    .post(requestBody)
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + config.getApiKey())
                    .addHeader("content-type", "application/json")
                    .build();

            // Executando a requisição
            Response res = client.newCall(req).execute();

            if (!res.isSuccessful()) {
                throw new ClientExeption("Unauthorized API Token.");
            }

            // If success, return client
            AbacatePayClientResponse abacatePayClientResponse = jsonUtilService.abacatePayClientResponseFromJsonToObject(res.body().string());
            return abacatePayClientResponse;

        } catch (Exception e) {
            // Captura todas as exceções e lança uma exceção genérica
            throw new RuntimeException("Error occurred while creating the client: " + e.getMessage(), e);
        }
    }

    @Override
    public List<AbacatePayClientResponse> listingClients(AbacatePayConfig config) {
        try {
            // GET to list Clients
            Request req = new Request.Builder()
                    .url(config.getBaseUrl() + "/customer/list")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + config.getApiKey())
                    .addHeader("content-type", "application/json")
                    .build();

            // Exec Req
            try (Response res = client.newCall(req).execute()) {
                if (!res.isSuccessful()) {
                    throw new ClientExeption("Unauthorized API Token");
                }
                //If null throw exception
                if (res.body() == null) {
                    throw new RuntimeException("Response body is null");
                }

                String responseBody = res.body().string();
                return jsonUtilService.abacatePayClientResponseFromJsonArrayToObject(responseBody);

            } catch (Exception e) {
                throw new RuntimeException("Error during the API request: " + e.getMessage(), e);
            }
        } catch (Exception e) {
            // Captura e lança exceção detalhada para erros gerais
            throw new RuntimeException("Error occurred while making the request: " + e.getMessage(), e);
        }
    }
}
