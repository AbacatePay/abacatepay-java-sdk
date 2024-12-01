package com.abacatepay.clients.exceptions.handlers;

import com.abacatepay.clients.exceptions.FeignCustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustomExceptionDecoder implements ErrorDecoder {

    private static final ErrorDecoder DECODER = new Default();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        try {
            ApiExceptionModel exception = MAPPER.readValue(
                    response.body().asInputStream(), ApiExceptionModel.class
            );
            return new FeignCustomException(
                    response.status(), exception.getError(), response.request()
            );
        } catch (IOException e) {
            return DECODER.decode(s, response);
        }
    }
}
