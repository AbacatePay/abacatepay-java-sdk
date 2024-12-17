package com.abacatepay.model.Billing;

import com.abacatepay.Models.Billing.CreateBillingResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreateBillingResponseTest {

    @Test
    void testErrorConstructor() {
        CreateBillingResponse response = new CreateBillingResponse("Error occurred");
        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
    }

    @Test
    void testGettersAndSetters() {
        CreateBillingResponse response = new CreateBillingResponse();
        response.setError("Error occurred");
        response.setBilling(null);

        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
        assertNull(response.getBilling(), "Billing field must be set correctly");
    }
}