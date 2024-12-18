package com.abacatepay.Utils.JsonUtils;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Models.AbacatePayClient.MetaData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JsonUtilServiceImpl implements JsonUtilService {

    // REQUESTS
    @Override
    public JSONObject createAbacatePayClientJson(AbacatePayClientRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", request.getName());
        jsonObject.put("cellphone", request.getCellphone());
        jsonObject.put("email", request.getEmail());
        jsonObject.put("taxId", request.getTaxId());
        return jsonObject;
    }

    // RESPONSES
    @Override
    public AbacatePayClientResponse abacatePayClientResponseFromJsonToObject(String response) {
        JSONObject jsonObject = new JSONObject(response);

        // Verificando se o campo 'metadata' existe no JSON
        if (!jsonObject.has("metadata")) {
            throw new IllegalArgumentException("Missing metadata in response");
        }

        MetaData metaData = MetaData.builder()
                .name(jsonObject.getJSONObject("metadata").optString("name", "Unknown"))
                .cellphone(jsonObject.getJSONObject("metadata").optString("cellphone", "Unknown"))
                .email(jsonObject.getJSONObject("metadata").optString("email", "Unknown"))
                .taxId(jsonObject.getJSONObject("metadata").optString("taxId", "Unknown"))
                .build();

        return AbacatePayClientResponse.builder()
                .id(jsonObject.optString("id", "Unknown"))
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
}
