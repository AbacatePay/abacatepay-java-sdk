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

    public static AbacatePayClient create(
            String baseUrl, RequestInterceptor requestInterceptor
    ) {
        MAPPER.registerModule(new JavaTimeModule());

        return Feign.builder()
                .decoder(new JacksonDecoder(MAPPER))
                .encoder(new JacksonEncoder(MAPPER))
                .errorDecoder(new CustomExceptionDecoder())
                .requestInterceptor(requestInterceptor)
                .target(AbacatePayClient.class, baseUrl);
    }
}
