package com.abacatepay.API.ClientService;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;

public interface ClientService {
    AbacatePayClientResponse createNewClient(AbacatePayClientRequest abacatePayClientRequest);
}
