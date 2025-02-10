package com.abacatepay.model.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ListCustomerResponseTest {
    @Test
    void testErrorConstructor() {
        ListCustomerResponse response = new ListCustomerResponse("Error occurred");
        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
    }

    @Test
    void testGettersAndSetters() {
        ListCustomerResponse response = new ListCustomerResponse();
        response.setError("Error occurred");
        response.setData(null);

        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
        assertNull(response.getData(), "Data field must be set correctly");
    }
}
