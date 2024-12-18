package com.abacatepay.Utils.JsonUtils;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import org.json.JSONObject;

public interface JsonUtilService {
    AbacatePayClientResponse abacatePayClientResponseFromJsonToObject(String string);

    JSONObject createAbacatePayClientJson(AbacatePayClientRequest request);
}
