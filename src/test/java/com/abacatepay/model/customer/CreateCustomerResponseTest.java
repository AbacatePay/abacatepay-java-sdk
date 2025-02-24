package com.abacatepay.model.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreateCustomerResponseTest {
    @Test
    void testErrorConstructor() {
        CreateCustomerResponse response = new CreateCustomerResponse("Error occurred");
        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
    }

    @Test
    void testGettersAndSetters() {
        CreateCustomerResponse response = new CreateCustomerResponse();
        response.setError("Error occurred");
        response.setData(null);

        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
        assertNull(response.getData(), "Data field must be set correctly");
    }
}
