package com.abacatepay;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.services.IAbacatePayBilling;
import com.abacatepay.services.IAbacatePayCustomer;
import com.abacatepay.services.impl.AbacatePayBilling;
import com.abacatepay.services.impl.AbacatePayCustomer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

class AbacatePayTest {

    @Mock
    private AbacatePayClient abacatePayClient;
    private AbacatePay abacatePay;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        abacatePay = new AbacatePay("apiKey");
        setClientMock(abacatePay, abacatePayClient);
    }

    private void setClientMock(AbacatePay abacatePay, AbacatePayClient mockClient) throws Exception {
        Field clientField = AbacatePay.class.getDeclaredField("client");
        clientField.setAccessible(true);
        clientField.set(abacatePay, mockClient);
    }

    @Test
    void testBilling() {
        IAbacatePayBilling billingService = abacatePay.billing();
        Assertions.assertNotNull(billingService);
        Assertions.assertTrue(billingService instanceof AbacatePayBilling);
    }

    @Test
    void testCustomer() {
        IAbacatePayCustomer customerService = abacatePay.customer();
        Assertions.assertNotNull(customerService);
        Assertions.assertTrue(customerService instanceof AbacatePayCustomer);
    }

    @Test
    void shouldThrowsIllegalArgumentExceptionIfApiKeyIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            abacatePay = new AbacatePay(null);
        }, "Should throw an IllegalArgumentException");
    }

    @Test
    void shouldThrowsIllegalArgumentExceptionIfApiKeyIsBlank() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            abacatePay = new AbacatePay("");
        }, "Should throw an IllegalArgumentException");
    }
}
