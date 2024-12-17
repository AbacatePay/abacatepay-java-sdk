package com.abacatepay.Models;

import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import com.abacatepay.Models.Billing.ListBillingResponse;

public interface IAbacatePayBilling {
    CreateBillingResponse create(CreateBillingData data);
    ListBillingResponse list();
}
