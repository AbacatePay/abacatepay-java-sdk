package com.abacatepay.model.Billing;

import com.abacatepay.Models.Billing.CustomerMetadata;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMetadataTest {

    @Test
    void testGettersAndSetters() {
        CustomerMetadata customerMetadata = new CustomerMetadata();
        customerMetadata.setName("John Doe");
        customerMetadata.setCellphone("123456789");
        customerMetadata.setEmail("john.doe@example.com");
        customerMetadata.setTaxId("12345678901");

        assertEquals("John Doe", customerMetadata.getName(), "Name field must be set correctly");
        assertEquals("123456789", customerMetadata.getCellphone(), "Cellphone field must be set correctly");
        assertEquals("john.doe@example.com", customerMetadata.getEmail(), "Email field must be set correctly");
        assertEquals("12345678901", customerMetadata.getTaxId(), "Tax ID field must be set correctly");
    }
}