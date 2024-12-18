package com.abacatepay.API.AbacatePayService;

import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import org.springframework.stereotype.Service;

@Service
public class AbacatePayServiceImpl implements AbacatePayService {
    //This method create a billing in a AbacatePay Client
    @Override
    public CreateBillingResponse createBilling(CreateBillingData billingData) {
        return null;
    }
}
