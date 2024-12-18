package com.abacatepay.Utils.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "abacatepay")
public class AbacatePayConfig {
    private String baseUrl;
    private String apiKey;

}
