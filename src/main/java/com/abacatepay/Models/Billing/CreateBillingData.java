package com.abacatepay.Models.Billing;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreateBillingData {
    private BillingKind frequency;
    private List<BillingMethod> methods;
    private List<CreateBillingProduct> products;
    private String returnUrl;
    private String completionUrl;
    private String customerId;
    private AbacatePayClientRequest customer;
}
