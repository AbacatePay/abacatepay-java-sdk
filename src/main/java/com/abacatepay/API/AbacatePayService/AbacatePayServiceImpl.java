package com.abacatepay.API.AbacatePayService;

import com.abacatepay.API.ClientService.ClientService;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbacatePayServiceImpl implements AbacatePayService {
    private final ClientService clientService;

    public AbacatePayServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    //This method create a billing in a AbacatePay Client
    @Override
    public CreateBillingResponse createBilling(CreateBillingData billingData) {
        return null;
    }

    @Override
    public AbacatePayClientResponse createNewClient(AbacatePayClientRequest abacatePayClientRequest, AbacatePayConfig config) {
        return clientService.createNewClient(abacatePayClientRequest, config);
    }

    @Override
    public List<AbacatePayClientResponse> listingClients(AbacatePayConfig config) {
        return clientService.listingClients(config);
    }
}
