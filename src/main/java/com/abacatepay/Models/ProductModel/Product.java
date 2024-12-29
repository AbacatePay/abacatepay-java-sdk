package com.abacatepay.Models.ProductModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String Id;
    private Integer quantity;
    private String externalId;
}