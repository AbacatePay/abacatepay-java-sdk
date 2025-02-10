package com.abacatepay.services.impl;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.model.customer.CreateCustomerResponse;
import com.abacatepay.model.customer.CustomerMetadata;
import com.abacatepay.model.customer.ListCustomerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atMostOnce;

public class AbacatePayCustomerTest {

    @Mock
    private AbacatePayClient abacatePayClient;

    @InjectMocks
    private AbacatePayCustomer abacatePayCustomer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCreateCustomerResponseOnSuccess() {
        CreateCustomerResponse expectedResponse = new CreateCustomerResponse();
        CustomerMetadata request = CustomerMetadata.builder().build();

        when(abacatePayClient.createCustomer(request))
                .thenReturn(expectedResponse);

        CreateCustomerResponse result = abacatePayCustomer.create(request);
        verify(abacatePayClient, atMostOnce()).createCustomer(request);
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldCreateCustomerThrowsAnException() {
        CreateCustomerResponse expectedResponse = new CreateCustomerResponse("Unauthorized");
        CustomerMetadata request = CustomerMetadata.builder().build();

        when(abacatePayClient.createCustomer(request))
                .thenThrow(new IllegalArgumentException("Unauthorized"));

        CreateCustomerResponse result = abacatePayCustomer.create(request);
        verify(abacatePayClient, atMostOnce()).createCustomer(request);
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldReturnListCustomersResponseOnSuccess() {
        ListCustomerResponse expectedResponse = new ListCustomerResponse();

        when(abacatePayClient.listCustomers())
                .thenReturn(expectedResponse);

        ListCustomerResponse result = abacatePayCustomer.list();
        verify(abacatePayClient, atMostOnce()).listCustomers();
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldListCustomersThrowsAnException() {
        ListCustomerResponse expectedResponse = new ListCustomerResponse("Unauthorized");

        when(abacatePayClient.listCustomers())
                .thenThrow(new IllegalArgumentException("Unauthorized"));

        ListCustomerResponse result = abacatePayCustomer.list();
        verify(abacatePayClient, atMostOnce()).listCustomers();
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }
}
