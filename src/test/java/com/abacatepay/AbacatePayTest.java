package com.abacatepay;

import com.abacatepay.clients.AbacatePayClient;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

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
}
