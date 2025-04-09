package com.abacatepay.model.pixqrcode;

import com.abacatepay.model.billing.CustomerMetadata;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreatePixQRCodeDataTest {
    @Test
    void testDefaultConstructor() {
        CreatePixQRCodeData response = new CreatePixQRCodeData();
        assertNull(response.getDescription());
    }

    @Test
    void testGettersAndSetters() {
        CreatePixQRCodeData response = new CreatePixQRCodeData();
        response.setAmount(BigDecimal.ONE);
        response.setDescription("hello world");
        response.setExpiresIn(1000000L);
        CustomerMetadata customerMetadata = new CustomerMetadata();
        response.setCustomerMetadata(customerMetadata);

        assertEquals(BigDecimal.ONE, response.getAmount());
        assertEquals("hello world", response.getDescription());
        assertEquals(1000000L, response.getExpiresIn());
        assertEquals(response.getCustomerMetadata(), customerMetadata);
    }
}
