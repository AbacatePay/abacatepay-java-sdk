package com.abacatepay.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private String id;
    private CustomerMetadata metadata;
}
