package com.abacatepay.Models.Billing;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListBillingResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Billing> billings;

    public ListBillingResponse(String error) {
        this.error = error;
    }
}
