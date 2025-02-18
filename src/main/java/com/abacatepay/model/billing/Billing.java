package com.abacatepay.model.billing;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Billing {
    private String id;
    private String url;
    private BigDecimal amount;
    private BillingStatus status;
    private Boolean devMode;
    private List<BillingMethod> methods;
    private List<Product> products;
    private BillingKind frequency;

    @JsonFormat(timezone = "America/Sao_Paulo", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime nextBilling;

    private Customer customer;

    @JsonFormat(timezone = "America/Sao_Paulo", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    @JsonFormat(timezone = "America/Sao_Paulo", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedAt;
}

@Data
class Product {
    private String productId;
    private Integer quantity;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Customer {
    private String id;
    private CustomerMetadata metadata;
}