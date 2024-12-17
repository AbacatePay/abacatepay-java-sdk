package com.abacatepay.Models.Billing;

import lombok.Data;

@Data
public class CustomerMetadata {
    private String name;
    private String cellphone;
    private String email;
    private String taxId;
}
