package com.abacatepay.Client.exceptions.handlers;

import com.abacatepay.Models.Client.exceptions.handlers.ApiExceptionModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiExceptionModelTest {

    @Test
    void testErrorField() {
        ApiExceptionModel apiException = new ApiExceptionModel();
        String expectedError = "Some error occurred";

        apiException.setError(expectedError);
        String actualError = apiException.getError();

        assertEquals(expectedError, actualError, "Error field must be set correctly");
    }

    @Test
    void testToString() {
        ApiExceptionModel apiException = new ApiExceptionModel();
        String expectedError = "Some error occurred";
        apiException.setError(expectedError);

        String actualString = apiException.toString();

        assertEquals("ApiExceptionModel(error=Some error occurred)", actualString, "toString must return the correct string representation");
    }
}