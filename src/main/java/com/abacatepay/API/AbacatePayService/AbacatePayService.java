package com.abacatepay.API.AbacatePayService;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;

import java.util.List;

public interface AbacatePayService {
    CreateBillingResponse createBilling(CreateBillingData billingData);
    List<CreateBillingResponse> listingBillings();
    AbacatePayClientResponse createNewClient(AbacatePayClientRequest abacatePayClientRequest);
    List<AbacatePayClientResponse> listingClients();
}
