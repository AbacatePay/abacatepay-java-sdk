package com.abacatepay.Utils.Config;

import lombok.Data;

@Data
public class AbacatePayConfig {
    private final String API_KEY;
    public AbacatePayConfig(String apiKey){
        this.API_KEY = apiKey;
    }
}
