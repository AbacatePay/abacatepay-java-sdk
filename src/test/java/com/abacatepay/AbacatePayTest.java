package com.abacatepay;

import com.abacatepay.clients.AbacatePayClient;
import com.abacatepay.model.AbacatePayBilling;
import com.abacatepay.model.IAbacatePayBilling;
import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.mockito.Mockito.*;

class AbacatePayTest {

    @Mock
    private AbacatePayClient mockClient;

    private AbacatePay abacatePay;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        abacatePay = new AbacatePay("apiKey");

        IAbacatePayBilling realBilling = new AbacatePayBilling(mockClient);

        setClientMock(abacatePay, realBilling);
    }

    private void setClientMock(AbacatePay abacatePay, IAbacatePayBilling realBilling) throws Exception {
        Field clientField = AbacatePay.class.getDeclaredField("billing");
        clientField.setAccessible(true);
        clientField.set(abacatePay, realBilling);
    }

    @Test
    void shouldReturnCreateBillingResponseOnSuccess() {
        CreateBillingData data = CreateBillingData.builder().build();
        CreateBillingResponse expectedResponse = new CreateBillingResponse();

        when(mockClient.create(data)).thenReturn(expectedResponse);

        CreateBillingResponse result = abacatePay.billing().create(data);

        verify(mockClient, atMostOnce()).create(data);
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldCreateBillingReturnErrorResponseWhenClientFails() {
        CreateBillingData data = CreateBillingData.builder().build();
        String errorMessage = "Internal Server Error";

        when(mockClient.create(data)).thenThrow(new IllegalArgumentException(errorMessage));

        CreateBillingResponse result = abacatePay.billing().create(data);

        verify(mockClient, atMostOnce()).create(data);
        Assertions.assertNotNull(result, "Should not return null");
        Assertions.assertEquals(errorMessage, result.getError());
    }

    @Test
    void shouldReturnBillingListResponseOnSucess() {
        ListBillingResponse expectedResponse = new ListBillingResponse();

        when(mockClient.list()).thenReturn(expectedResponse);

        ListBillingResponse result = abacatePay.billing().list();
        verify(mockClient, atMostOnce()).list();
        Assertions.assertEquals(expectedResponse, result, "Should return the expected response");
    }

    @Test
    void shouldListBillingReturnErrorResponseWhenClientFails() {
        String errorMessage = "Internal Server Error";

        when(mockClient.list()).thenThrow(new IllegalArgumentException(errorMessage));

        ListBillingResponse result = abacatePay.billing().list();

        verify(mockClient, atMostOnce()).list();
        Assertions.assertNotNull(result, "Should not return null");
        Assertions.assertEquals(errorMessage, result.getError());
    }

    @Test
    void shouldThrowExceptionWhenApiKeyIsMissing() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AbacatePay("");
        });

        Assertions.assertEquals("API key not provided", exception.getMessage());
    }

    @Test
    void testResolveVersionDirectly() throws Exception {
        Method method = AbacatePay.class.getDeclaredMethod("resolveVersion");
        method.setAccessible(true);

        String version = (String) method.invoke(null);

        Assertions.assertNotNull(version);
        Assertions.assertEquals("dev", version, "local environment should return 'dev' as version");
    }

    @Test
    void testBuildUserAgentDirectly() throws Exception {
        Method method = AbacatePay.class.getDeclaredMethod("buildUserAgent");
        method.setAccessible(true);

        String userAgent = (String) method.invoke(null);

        String currentJava = System.getProperty("java.version");
        String currentOs = System.getProperty("os.name");

        Assertions.assertNotNull(userAgent);
        Assertions.assertTrue(userAgent.startsWith("abacatepay-java-sdk/"), "Should start with the SDK name");
        Assertions.assertTrue(userAgent.contains("Java/" + currentJava), "Should contain the Java tag version");
        Assertions.assertTrue(userAgent.contains(currentOs), "Should contain the OS name");
    }
}
