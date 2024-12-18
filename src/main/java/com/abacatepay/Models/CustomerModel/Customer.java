package com.abacatepay.Models.CustomerModel;

import com.abacatepay.Models.Billing.CustomerMetadata;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private String id;
    private CustomerMetadata metadata;
}