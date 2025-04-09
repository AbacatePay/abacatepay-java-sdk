package com.abacatepay.model.pixqrcode;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreatePixQRCodeResponseTest {
    @Test
    void testErrorConstructor() {
        CreatePixQRCodeResponse response = new CreatePixQRCodeResponse("Error occurred");
        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
    }

    @Test
    void testGettersAndSetters() {
        CreatePixQRCodeResponse response = new CreatePixQRCodeResponse();
        response.setError("Error occurred");
        response.setData(null);

        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
        assertNull(response.getData());
    }
}
