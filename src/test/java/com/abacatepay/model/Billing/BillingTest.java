package com.abacatepay.model.Billing;

import com.abacatepay.Models.Billing.Billing;
import com.abacatepay.Models.Billing.BillingStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BillingTest {

    @Test
    void testGettersAndSetters() {
        Billing billing = new Billing();
        billing.setId("billing-123");
        billing.setUrl("http://billing.url");
        billing.setAmount(BigDecimal.valueOf(100.00));
        billing.setStatus(BillingStatus.PAID);
        billing.setDevMode(true);

        assertEquals("billing-123", billing.getId(), "Id must be set correctly");
        assertEquals("http://billing.url", billing.getUrl(), "URL must be set correctly");
        assertEquals(BigDecimal.valueOf(100.00), billing.getAmount(), "Amount must be set correctly");
        assertEquals(BillingStatus.PAID, billing.getStatus(), "Status must be set correctly");
        assertTrue(billing.getDevMode(), "Dev mode must be set correctly");
    }
}