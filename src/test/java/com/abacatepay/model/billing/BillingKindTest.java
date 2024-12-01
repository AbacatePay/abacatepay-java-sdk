package com.abacatepay.model.billing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillingKindTest {

    @Test
    void testEnumValues() {
        assertEquals(1, BillingKind.values().length, "Should have only one value");
        assertEquals(BillingKind.ONE_TIME, BillingKind.valueOf("ONE_TIME"), "Should have the correct value");
    }
}