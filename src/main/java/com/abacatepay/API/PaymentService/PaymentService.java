package com.abacatepay.API.PaymentService;

import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;

import java.util.List;

public interface PaymentService {
    CreateBillingResponse createBilling(CreateBillingData billingData);

    List<CreateBillingResponse> listingBillings(AbacatePayConfig config);
}
