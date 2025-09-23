package com.abacatepay;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import com.abacatepay.model.withdraw.Withdraw;
import com.abacatepay.model.withdraw.payloads.request.CreateWithdrawRequest;
import com.abacatepay.model.withdraw.payloads.response.CreateWithdrawResponse;
import com.abacatepay.model.withdraw.payloads.response.ListWithdrawResponse;
import com.abacatepay.model.withdraw.payloads.response.WithdrawResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class AbacatePayTest {

    @Mock
    private AbacatePayClient abacatePayClient;

    @InjectMocks
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
        CreateBillingResponse expectedResponse = new CreateBillingResponse("");

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
    void shouldCreateWithdrawReturnsResponse() {
        final CreateWithdrawRequest request = CreateWithdrawRequest.builder()
                .build();
        final CreateWithdrawResponse response = new CreateWithdrawResponse();

        when(abacatePayClient.createWithdraw(request))
                .thenReturn(response);

        final CreateWithdrawResponse result = abacatePay.withdraw().createWithdraw(request);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(response, result, "Should return the expected response");
    }

    @Test
    void shouldCreateWithdrawThrowsAnException() {
        final CreateWithdrawRequest request = CreateWithdrawRequest.builder()
                .build();
        final CreateWithdrawResponse response = new CreateWithdrawResponse("API key not provided");

        when(abacatePayClient.createWithdraw(request))
                .thenThrow(new IllegalArgumentException("API key not provided"));

        final CreateWithdrawResponse result = abacatePay.withdraw().createWithdraw(request);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(response, result, "Should return the expected response");
    }

    @Test
    void shouldListWithdrawalsReturnsResponse() {
        final Withdraw withdraw = Withdraw.builder()
                .amount(300.00)
                .build();
        final ListWithdrawResponse response = new ListWithdrawResponse();
        response.setWithdrawals(new ArrayList<>(Arrays.asList(withdraw)));

        when(abacatePayClient.listWithdraw())
                .thenReturn(response);

        final ListWithdrawResponse result = abacatePay.withdraw().listWithdraw();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(response, result, "Should return the expected response");
    }

    @Test
    void shouldListWithdrawalsThrowsAnException() {
        final ListWithdrawResponse response = new ListWithdrawResponse("API key not provided");

        when(abacatePayClient.listWithdraw())
                .thenThrow(new IllegalArgumentException("API key not provided"));

        final ListWithdrawResponse result = abacatePay.withdraw().listWithdraw();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(response, result, "Should return the expected response");
    }

    @Test
    void shouldGetWithdrawsReturnsResponse() {
        final Withdraw withdraw = Withdraw.builder()
                .amount(300.00)
                .build();
        final WithdrawResponse response = new WithdrawResponse();
        response.setWithdraw(withdraw);

        when(abacatePayClient.getWithdraw(ArgumentMatchers.anyString()))
                .thenReturn(response);

        final WithdrawResponse result = abacatePay.withdraw().getWithdraw("withdraw-1234");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(response, result, "Should return the expected response");
    }

    @Test
    void shouldGetWithdrawsThrowsAnException() {
        final WithdrawResponse response = new WithdrawResponse("API key not provided");

        when(abacatePayClient.getWithdraw(ArgumentMatchers.anyString()))
                .thenThrow(new IllegalArgumentException("API key not provided"));

        final WithdrawResponse result = abacatePay.withdraw().getWithdraw("withdraw-1234");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(response, result, "Should return the expected response");
    }
}
