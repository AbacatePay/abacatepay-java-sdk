package com.abacatepay.API.PaymentService;

import com.abacatepay.Models.Billing.Billing;
import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface PaymentService {
    Optional<Billing> createBilling(CreateBillingData billingData, AbacatePayConfig config);

    Optional<List<Billing>> listingBillings(AbacatePayConfig config);
}
