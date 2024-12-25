package com.abacatepay.Utils.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "abacatepay")
@Configuration
@Component
public class AbacatePayConfig {
    private String baseUrl;
    private String apiKey;
}
