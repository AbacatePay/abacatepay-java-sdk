package com.abacatepay.API.PaymentService;

import com.abacatepay.Models.Billing.Billing;
import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;

import java.util.List;

public interface PaymentService {
    Billing createBilling(CreateBillingData billingData, AbacatePayConfig config);

    List<CreateBillingResponse> listingBillings(AbacatePayConfig config);
}
