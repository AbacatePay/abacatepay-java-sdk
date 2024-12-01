package com.abacatepay.model.billing;

import lombok.Data;

@Data
public class CustomerMetadata {
    private String name;
    private String cellphone;
    private String email;
    private String taxId;
}
