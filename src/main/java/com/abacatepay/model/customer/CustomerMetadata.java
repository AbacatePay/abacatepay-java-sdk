package com.abacatepay.model.customer;

import lombok.Data;

@Data
public class CustomerMetadata {
    private String name;
    private String cellphone;
    private String email;
    private String taxId;
}
