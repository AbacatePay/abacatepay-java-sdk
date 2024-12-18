package com.abacatepay.APITests.ClientTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.abacatepay.API.ClientService.ClientServiceImpl;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Utils.JsonUtils.JsonUtilService;
import com.abacatepay.Utils.Config.AbacatePayConfig;
import com.abacatepay.Utils.Exceptions.ClientExeption;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClientServiceImplTest {

    @Mock
    private JsonUtilService jsonUtilService;

    @Mock
    private OkHttpClient okHttpClient;

    @Mock
    private Response responseMock;
    private Call mockCall;
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clientService = new ClientServiceImpl(jsonUtilService);
    }

    @Test
    public void testCreateNewClient_Success() throws IOException {
        // Mock da resposta JSON do serviço
        String jsonResponse = "{ \"id\": \"123\", \"metadata\": { \"name\": \"John Doe\" }}";
        when(responseMock.isSuccessful()).thenReturn(true);
        when(responseMock.body()).thenReturn(ResponseBody.create(jsonResponse, MediaType.get("application/json")));
        when(okHttpClient.newCall(any(Request.class))).thenReturn(mockCall);

        // Mock da utilidade de criação do JSON
        AbacatePayClientRequest clientRequest = new AbacatePayClientRequest("John Doe", "123456789", "john@example.com", "12345678901");
        when(jsonUtilService.createAbacatePayClientJson(clientRequest)).thenReturn(new JSONObject(jsonResponse));

        // Mock da configuração
        AbacatePayConfig config = new AbacatePayConfig();
        config.setApiKey("fakeApiKey");
        config.setBaseUrl("https://api.abacatepay.com");

        // Chamada do método
        AbacatePayClientResponse response = clientService.createNewClient(clientRequest, config);

        // Verificar se a resposta é correta
        assertNotNull(response);
        assertEquals("123", response.getId());
        assertEquals("John Doe", response.getMetaData().getName());
    }

    @Test
    public void testCreateNewClient_Unauthorized() throws IOException {
        // Mock para resposta de erro 401
        when(responseMock.isSuccessful()).thenReturn(false);
        when(responseMock.code()).thenReturn(401);
        when(okHttpClient.newCall(any(Request.class))).thenReturn(mockCall);

        AbacatePayConfig config = new AbacatePayConfig();
        config.setApiKey("fakeApiKey");
        config.setBaseUrl("https://api.abacatepay.com");

        // Chamada do método e verificação da exceção
        AbacatePayClientRequest clientRequest = new AbacatePayClientRequest("John Doe", "123456789", "john@example.com", "12345678901");
        ClientExeption exception = assertThrows(ClientExeption.class, () -> {
            clientService.createNewClient(clientRequest, config);
        });

        assertEquals("Unauthorized API Token.", exception.getMessage());
    }
}
