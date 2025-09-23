package com.abacatepay.model.withdraw.payloads.response;

import com.abacatepay.model.withdraw.Withdraw;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateWithdrawResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Withdraw withdraw;

    public CreateWithdrawResponse(String error) {
        this.error = error;
    }
}
