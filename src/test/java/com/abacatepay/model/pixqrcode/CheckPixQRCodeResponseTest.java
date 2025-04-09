package com.abacatepay.model.pixqrcode;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CheckPixQRCodeResponseTest {

    @Test
    void testErrorConstructor() {
        CheckPixQRCodeResponse response = new CheckPixQRCodeResponse("Error occurred");
        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
    }

    @Test
    void testGettersAndSetters() {
        CheckPixQRCodeResponse response = new CheckPixQRCodeResponse();
        response.setError("Error occurred");
        response.setData(null);

        assertEquals("Error occurred", response.getError(), "Error field must be set correctly");
        assertNull(response.getData());
    }
}