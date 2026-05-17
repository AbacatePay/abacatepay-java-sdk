package com.abacatepay.model;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class AbacatePayBillingTest {

    @Mock
    private AbacatePayClient mockClient;

    private AbacatePayBilling abacatePayBilling;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        this.abacatePayBilling = new AbacatePayBilling(mockClient);
    }

    @Test
    void shouldReturnCreateBillingResponseOnSuccess() {
        CreateBillingData data = CreateBillingData.builder().build();
        CreateBillingResponse expectedResponse = new CreateBillingResponse();

        when(mockClient.create(data)).thenReturn(expectedResponse);
        
        CreateBillingResponse result = abacatePayBilling.create(data);
        
        verify(mockClient, atMostOnce()).create(data);
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldReturnErrorResponseWhenCreateThrowsIllegalArgumentException() {
        CreateBillingData data = CreateBillingData.builder().build();
        String errorMessage = "Internal Server Error";
        
        when(mockClient.create(data)).thenThrow(new IllegalArgumentException(errorMessage));
        
        CreateBillingResponse result = abacatePayBilling.create(data);
        
        verify(mockClient, atMostOnce()).create(data);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(errorMessage, result.getError(), "Should return the expected response");
    }

    @Test
    void shouldReturnErrorResponseWhenCreateThrowsFeignException() {
        CreateBillingData data = CreateBillingData.builder().build();
        
        FeignException mockFeignException = mock(FeignException.class);
        when(mockFeignException.getMessage()).thenReturn("HTTP 401 Unauthorized");

        when(mockClient.create(data)).thenThrow(mockFeignException);
        
        CreateBillingResponse result = abacatePayBilling.create(data);
        
        verify(mockClient, atMostOnce()).create(data);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("HTTP 401 Unauthorized", result.getError());
    }

    @Test
    void shouldReturnListBillingResponseOnSuccess() {
        ListBillingResponse expectedResponse = new ListBillingResponse();
        when(mockClient.list()).thenReturn(expectedResponse);
        
        ListBillingResponse result = abacatePayBilling.list();
        
        verify(mockClient, atMostOnce()).list();
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldReturnErrorResponseWhenListThrowsIllegalArgumentException() {
        String errorMessage = "Internal Server Error";
        when(mockClient.list()).thenThrow(new IllegalArgumentException(errorMessage));
        
        ListBillingResponse result = abacatePayBilling.list();
        
        verify(mockClient, atMostOnce()).list();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(errorMessage, result.getError());
    }

    @Test
    void shouldReturnErrorResponseWhenListThrowsFeignException() {
        FeignException mockFeignException = mock(FeignException.class);
        when(mockFeignException.getMessage()).thenReturn("HTTP 500 Internal Server Error");

        when(mockClient.list()).thenThrow(mockFeignException);
        
        ListBillingResponse result = abacatePayBilling.list();
        
        verify(mockClient, atMostOnce()).list();
        Assertions.assertNotNull(result);
        Assertions.assertEquals("HTTP 500 Internal Server Error", result.getError());
    }
}
