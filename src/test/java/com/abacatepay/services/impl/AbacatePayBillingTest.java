package com.abacatepay.services.impl;

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

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atMostOnce;

public class AbacatePayBillingTest {

    @Mock
    private AbacatePayClient abacatePayClient;

    @InjectMocks
    private AbacatePayBilling service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCreateBillingResponseOnSuccess() {
        CreateBillingData data = CreateBillingData.builder().build();
        CreateBillingResponse expectedResponse = new CreateBillingResponse();

        when(abacatePayClient.create(data)).thenReturn(expectedResponse);

        CreateBillingResponse result = service.create(data);
        verify(abacatePayClient, atMostOnce()).create(data);
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldCreateBillingThrowsAnException() {
        CreateBillingData data = CreateBillingData.builder().build();
        CreateBillingResponse expectedResponse = new CreateBillingResponse("API key not provided");

        when(abacatePayClient.create(data)).thenThrow(new IllegalArgumentException("API key not provided"));

        CreateBillingResponse result = service.create(data);
        verify(abacatePayClient, atMostOnce()).create(data);
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldReturnBillingListResponseOnSucess() {
        ListBillingResponse expectedResponse = new ListBillingResponse();

        when(abacatePayClient.list()).thenReturn(expectedResponse);

        ListBillingResponse result = service.list();
        verify(abacatePayClient, atMostOnce()).list();
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldListBillingThrowsAnException() {
        ListBillingResponse expectedResponse = new ListBillingResponse("API key not provided");

        when(abacatePayClient.list()).thenThrow(new IllegalArgumentException("API key not provided"));

        ListBillingResponse result = service.list();
        verify(abacatePayClient, atMostOnce()).list();
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }
}
