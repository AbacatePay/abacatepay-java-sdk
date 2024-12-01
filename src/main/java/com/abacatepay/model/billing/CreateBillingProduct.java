package com.abacatepay.model.billing;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateBillingProduct {
    private String externalId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String description;
}
