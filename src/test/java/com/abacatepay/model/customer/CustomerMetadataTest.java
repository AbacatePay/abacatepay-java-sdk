package com.abacatepay.model.customer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMetadataTest {

    @Test
    void testGettersAndSetters() {
        CustomerMetadata customerMetadata = CustomerMetadata.builder()
                .name("John Doe")
                .cellphone("123456789")
                .email("john.doe@example.com")
                .taxId("12345678901")
                .build();

        assertEquals("John Doe", customerMetadata.getName(), "Name field must be set correctly");
        assertEquals("123456789", customerMetadata.getCellphone(), "Cellphone field must be set correctly");
        assertEquals("john.doe@example.com", customerMetadata.getEmail(), "Email field must be set correctly");
        assertEquals("12345678901", customerMetadata.getTaxId(), "Tax ID field must be set correctly");
    }
}