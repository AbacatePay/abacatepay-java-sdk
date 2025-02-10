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

        when(abacatePayClient.createBilling(data)).thenReturn(expectedResponse);

        CreateBillingResponse result = service.create(data);
        verify(abacatePayClient, atMostOnce()).createBilling(data);
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldCreateBillingThrowsAnException() {
        CreateBillingData data = CreateBillingData.builder().build();
        CreateBillingResponse expectedResponse = new CreateBillingResponse("API key not provided");

        when(abacatePayClient.createBilling(data)).thenThrow(new IllegalArgumentException("API key not provided"));

        CreateBillingResponse result = service.create(data);
        verify(abacatePayClient, atMostOnce()).createBilling(data);
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldReturnBillingListResponseOnSucess() {
        ListBillingResponse expectedResponse = new ListBillingResponse();

        when(abacatePayClient.listBillings()).thenReturn(expectedResponse);

        ListBillingResponse result = service.list();
        verify(abacatePayClient, atMostOnce()).listBillings();
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldListBillingThrowsAnException() {
        ListBillingResponse expectedResponse = new ListBillingResponse("API key not provided");

        when(abacatePayClient.listBillings()).thenThrow(new IllegalArgumentException("API key not provided"));

        ListBillingResponse result = service.list();
        verify(abacatePayClient, atMostOnce()).listBillings();
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }
}
