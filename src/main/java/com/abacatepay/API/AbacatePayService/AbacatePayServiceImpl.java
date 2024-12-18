package com.abacatepay.API.AbacatePayService;

import com.abacatepay.API.ClientService.ClientService;
import com.abacatepay.API.PaymentService.PaymentService;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Models.Billing.Billing;
import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbacatePayServiceImpl implements AbacatePayService {
    private final AbacatePayConfig abacatePayConfig;
    private final ClientService clientService;
    private final PaymentService paymentService;

    public AbacatePayServiceImpl(AbacatePayConfig abacatePayConfig, ClientService clientService, PaymentService paymentService) {
        this.abacatePayConfig = abacatePayConfig;
        this.clientService = clientService;
        this.paymentService = paymentService;
    }

    // Payment & Billing Actions
    @Override
    public Optional<Billing> createBilling(CreateBillingData billingData) {
        return paymentService.createBilling(billingData, abacatePayConfig);
    }

    @Override
    public Optional<List<Billing>> listingBillings() {
        return paymentService.listingBillings(abacatePayConfig);
    }

    // Client Actions
    @Override
    public AbacatePayClientResponse createNewClient(AbacatePayClientRequest abacatePayClientRequest) {
        return clientService.createNewClient(abacatePayClientRequest, abacatePayConfig);
    }

    @Override
    public List<AbacatePayClientResponse> listingClients() {
        return clientService.listingClients(abacatePayConfig);
    }
}
