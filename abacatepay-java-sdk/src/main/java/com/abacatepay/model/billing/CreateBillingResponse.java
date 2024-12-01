package com.abacatepay.model.billing;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class CreateBillingResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Billing billing;
}
