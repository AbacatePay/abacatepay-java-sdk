package com.abacatepay.model.billing;

import com.abacatepay.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
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

    @JsonFormat(timezone = DateUtils.DATE_TIMEZONE_DEFAULT, pattern = DateUtils.DATE_FORMAT_PATTERN)
    private LocalDateTime nextBilling;

    private Customer customer;
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