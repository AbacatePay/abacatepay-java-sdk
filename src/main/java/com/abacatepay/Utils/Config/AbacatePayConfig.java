package com.abacatepay.Utils.Config;

import lombok.Data;

@Data
public class AbacatePayConfig {
    private final String BASE_URL;
    private final String API_KEY;
    public AbacatePayConfig(String baseUrl, String apiKey){
        BASE_URL = baseUrl;
        this.API_KEY = apiKey;
    }
}
