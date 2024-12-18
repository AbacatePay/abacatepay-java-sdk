package com.abacatepay.Utils.JsonUtils;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Models.AbacatePayClient.MetaData;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class JsonUtilServiceImpl implements JsonUtilService{
    @Override
    public AbacatePayClientResponse abacatePayClientResponseFromJsonToObject(String response) {
        JSONObject jsonObject = new JSONObject(response);
        MetaData metaData = MetaData.builder()
                .name(jsonObject.getJSONObject("metadata").getString("name"))
                .cellphone(jsonObject.getJSONObject("metadata").getString("cellphone"))
                .email(jsonObject.getJSONObject("metadata").getString("email"))
                .taxId(jsonObject.getJSONObject("metadata").getString("taxId"))
                .build();
        return AbacatePayClientResponse.builder()
                .id(jsonObject.getString("id"))
                .metaData(metaData)
                .build();
    }

    @Override
    public JSONObject createAbacatePayClientJson(AbacatePayClientRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", request.getName());
        jsonObject.put("cellphone",request.getCellphone());
        jsonObject.put("email", request.getEmail());
        jsonObject.put("taxId", request.getTaxId());
        return jsonObject;
    }
}
