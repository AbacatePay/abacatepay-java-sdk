package com.abacatepay;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

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
    void shouldReturnCreateBillingResponseOnSuccess() {
        CreateBillingData data = CreateBillingData.builder().build();
        CreateBillingResponse expectedResponse = new CreateBillingResponse();

        when(abacatePayClient.create(data)).thenReturn(expectedResponse);

        CreateBillingResponse result = abacatePay.billing().create(data);
        verify(abacatePayClient, atMostOnce()).create(data);
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldCreateBillingThrowsAnException() {
        CreateBillingData data = CreateBillingData.builder().build();
        CreateBillingResponse expectedResponse = new CreateBillingResponse("API key not provided");

        when(abacatePayClient.create(data)).thenThrow(new IllegalArgumentException("API key not provided"));

        CreateBillingResponse result = abacatePay.billing().create(data);
        verify(abacatePayClient, atMostOnce()).create(data);
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldReturnBillingListResponseOnSucess() {
        ListBillingResponse expectedResponse = new ListBillingResponse();

        when(abacatePayClient.list()).thenReturn(expectedResponse);

        ListBillingResponse result = abacatePay.billing().list();
        verify(abacatePayClient, atMostOnce()).list();
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldListBillingThrowsAnException() {
        ListBillingResponse expectedResponse = new ListBillingResponse("API key not provided");

        when(abacatePayClient.list()).thenThrow(new IllegalArgumentException("API key not provided"));

        ListBillingResponse result = abacatePay.billing().list();
        verify(abacatePayClient, atMostOnce()).list();
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
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
