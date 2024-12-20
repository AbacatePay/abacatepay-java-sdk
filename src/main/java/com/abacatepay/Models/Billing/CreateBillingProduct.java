package com.abacatepay.Models.Billing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateBillingProduct {
    private String externalId;
    private String name;
    private Integer quantity;
    private int price;
    private String description;
    public CreateBillingProduct(){

    }
}
