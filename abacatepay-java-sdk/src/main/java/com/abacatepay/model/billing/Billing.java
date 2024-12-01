package com.abacatepay.model.billing;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Billing {
    private String id;
    private String url;
    private BigDecimal amount;
    private BillingStatus status;
    private Boolean devMode;
    private List<BillingMethod> methods;
    private List<Product> products;
    private BillingKind frequency;
    private String nextBilling;
    private Customer customer;
    private String createdAt;
    private String updatedAt;
}

@Data
class Product {
    private String productId;
    private Integer quantity;
}

@Data
class Customer {
    private String id;
    private CustomerMetadata metadata;
}