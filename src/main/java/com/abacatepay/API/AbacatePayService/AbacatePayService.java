package com.abacatepay.API.AbacatePayService;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Models.Billing.Billing;
import com.abacatepay.Models.Billing.CreateBillingData;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;
@Component
public interface AbacatePayService {
    Optional<Billing> createBilling(CreateBillingData billingData);
    Optional<List<Billing>> listingBillings();
    AbacatePayClientResponse createNewClient(AbacatePayClientRequest abacatePayClientRequest);
    List<AbacatePayClientResponse> listingClients();
}
