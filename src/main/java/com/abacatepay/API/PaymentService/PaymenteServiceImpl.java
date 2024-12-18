package com.abacatepay.API.PaymentService;

import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Utils.Config.AbacatePayConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymenteServiceImpl implements PaymentService {
    @Override
    public CreateBillingResponse createBilling(CreateBillingData billingData) {
        return null;
    }

    @Override
    public List<CreateBillingResponse> listingBillings(AbacatePayConfig config) {
        return List.of();
    }
}
