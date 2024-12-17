package com.abacatepay.model.Billing;

import com.abacatepay.Models.Billing.CreateBillingProduct;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreateBillingProductTest {

    @Test
    void testGettersAndSetters() {
        CreateBillingProduct product = CreateBillingProduct.builder()
                .externalId("ext-123")
                .name("Product 1")
                .quantity(2)
                .price(BigDecimal.valueOf(19.99))
                .description("Description of Product 1")
                .build();

        assertEquals("ext-123", product.getExternalId(), "External ID must be set correctly");
        assertEquals("Product 1", product.getName(), "Name must be set correctly");
        assertEquals(2, product.getQuantity(), "Quantity must be set correctly");
        assertEquals(BigDecimal.valueOf(19.99), product.getPrice(), "Price must be set correctly");
        assertEquals("Description of Product 1", product.getDescription(), "Description must be set correctly");
    }
}