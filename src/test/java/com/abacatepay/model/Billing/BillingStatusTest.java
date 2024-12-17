package com.abacatepay.model.Billing;

import com.abacatepay.Models.Billing.BillingStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillingStatusTest {

    @Test
    void testEnumValues() {
        assertEquals(5, BillingStatus.values().length, "Should have only five values");
        assertEquals(BillingStatus.PAID, BillingStatus.valueOf("PAID"), "Should have the correct value");
    }
}