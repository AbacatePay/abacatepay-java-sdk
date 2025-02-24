package com.abacatepay.clients.factories;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.clients.exceptions.handlers.CustomExceptionDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.RequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class AbacatePayClientFactory {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JavaTimeModule());
    }

    public static AbacatePayClient create(
            String baseUrl, String apiKey, String userAgent
    ) {
        return Feign.builder()
                .decoder(new JacksonDecoder(MAPPER))
                .encoder(new JacksonEncoder(MAPPER))
                .errorDecoder(new CustomExceptionDecoder())
                .requestInterceptor(requestInterceptor(apiKey, userAgent))
                .target(AbacatePayClient.class, baseUrl);
    }

    private static RequestInterceptor requestInterceptor(String apiKey, String userAgent) {
        return template -> {
            template.header("Authorization", "Bearer " + apiKey);
            template.header("Content-Type", "application/json");
            template.header("User-Agent", userAgent);
        };
    }
}
