package com.abacatepay.model.billing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillingMethodTest {

    @Test
    void testEnumValues() {
        assertEquals(1, BillingMethod.values().length, "Should have only one value");
        assertEquals(BillingMethod.PIX, BillingMethod.valueOf("PIX"), "Should have the correct value");
    }
}