package com.abacatepay.API.AbacatePayService;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;

import java.util.List;

public interface AbacatePayService {
    CreateBillingResponse createBilling(CreateBillingData billingData);
    AbacatePayClientResponse createNewClient(AbacatePayClientRequest abacatePayClientRequest, AbacatePayConfig config);
    List<AbacatePayClientResponse> listingClients(AbacatePayConfig config);
}
