package com.abacatepay.API.AbacatePayService;

import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;

public interface AbacatePayService {
    CreateBillingResponse createBilling(CreateBillingData billingData);

}
