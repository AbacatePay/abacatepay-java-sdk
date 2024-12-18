package com.abacatepay.Utils.Config;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class AbacatePayConfig {
    private String baseUrl;
    private String apiKey;
    public AbacatePayConfig(String baseUrl, String apiKey){
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

}
