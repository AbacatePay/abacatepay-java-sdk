package com.abacatepay.model.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerMetadata {
    private String name;
    private String cellphone;
    private String email;
    private String taxId;
}
