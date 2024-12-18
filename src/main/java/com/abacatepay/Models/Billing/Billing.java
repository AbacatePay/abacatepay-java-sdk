package com.abacatepay.Models.Billing;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Models.ProductModel.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
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

    private AbacatePayClientResponse customer;

    @JsonFormat(timezone = "America/Sao_Paulo", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    @JsonFormat(timezone = "America/Sao_Paulo", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedAt;
}



