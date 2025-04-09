package com.abacatepay.model.pixqrcode;

import com.abacatepay.model.billing.CustomerMetadata;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePixQRCodeData {
    private BigDecimal amount;
    private Long expiresIn;
    private String description;
    private CustomerMetadata customerMetadata;
}
