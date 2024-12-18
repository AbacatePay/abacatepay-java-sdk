package com.abacatepay.API.ClientService;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    AbacatePayClientResponse createNewClient(AbacatePayClientRequest abacatePayClientRequest, AbacatePayConfig config);
}
