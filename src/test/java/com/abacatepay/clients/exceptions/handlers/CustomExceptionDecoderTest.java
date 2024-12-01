package com.abacatepay.clients.exceptions.handlers;

import com.abacatepay.clients.exceptions.FeignCustomException;
import feign.FeignException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomExceptionDecoderTest {

    private CustomExceptionDecoder customExceptionDecoder;
    private Response mockResponse;

    @BeforeEach
    void setUp() {
        customExceptionDecoder = new CustomExceptionDecoder();
    }

    @Test
    void testDecodeReturnsFeignCustomException() {
        String errorJson = "{\"error\":\"Some error occurred\"}";
        mockResponse = Response.builder()
                .status(404)
                .body(new ByteArrayInputStream(errorJson.getBytes()), errorJson.length())
                .request(Request.create(Request.HttpMethod.GET, "http://localhost", Collections.emptyMap(), (byte[]) null, null))
                .build();

        Exception exception = customExceptionDecoder.decode("methodKey", mockResponse);

        assertEquals(FeignCustomException.class, exception.getClass(), "Exception must be FeignCustomException");
        FeignCustomException feignException = (FeignCustomException) exception;
        assertEquals(404, feignException.status(), "Status must be set correctly");
        assertEquals("Some error occurred", feignException.getMessage(), "Error message must be set correctly");
    }

    @Test
    void testDecodeHandlesMalformedJson() {
        String malformedJson = "{\"error\":\"Some error occurred\""; // Missing closing brace
        mockResponse = Response.builder()
                .status(401)
                .body(new ByteArrayInputStream(malformedJson.getBytes()), malformedJson.length())
                .request(Request.create(Request.HttpMethod.GET, "http://localhost", Collections.emptyMap(), (byte[]) null, null))
                .build();

        Exception exception = customExceptionDecoder.decode("methodKey", mockResponse);

        assertEquals(FeignException.Unauthorized.class, exception.getClass(), "Exception must be FeignException");
        FeignException feignException = (FeignException) exception;
        assertEquals(401, feignException.status(), "Status must be set correctly");
    }
}
