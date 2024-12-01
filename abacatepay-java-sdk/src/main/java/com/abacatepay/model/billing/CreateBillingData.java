package com.abacatepay.model.billing;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CreateBillingData {
    private BillingKind frequency;
    private List<BillingMethod> methods = new ArrayList<>();
    private List<CreateBillingProduct> products = new ArrayList<>();
    private String returnUrl;
    private String completionUrl;
}
