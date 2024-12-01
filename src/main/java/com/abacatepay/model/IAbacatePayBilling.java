package com.abacatepay.model;

import com.abacatepay.model.billing.CreateBillingData;
import com.abacatepay.model.billing.CreateBillingResponse;
import com.abacatepay.model.billing.ListBillingResponse;

public interface IAbacatePayBilling {
    CreateBillingResponse create(CreateBillingData data);
    ListBillingResponse list();
}
