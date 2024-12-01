package com.abacatepay.model.billing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListBillingResponseTest {

    @Test
    void testErrorConstructor() {
        ListBillingResponse response = new ListBillingResponse("Error occurred");
        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
    }

    @Test
    void testGettersAndSetters() {
        ListBillingResponse response = new ListBillingResponse();
        response.setError("Error occurred");
        response.setBillings(null);

        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
        assertNull(response.getBillings(), "Billings field must be set correctly");
    }
}