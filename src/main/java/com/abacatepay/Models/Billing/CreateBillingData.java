package com.abacatepay.Models.Billing;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateBillingData {
    private BillingKind frequency;
    private List<BillingMethod> methods;
    private List<CreateBillingProduct> products;
    private String returnUrl;
    private String completionUrl;
    private String customerId;
    private AbacatePayClientRequest customer;
}
