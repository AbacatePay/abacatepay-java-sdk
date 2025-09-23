package com.abacatepay.model.withdraw.payloads.response;

import com.abacatepay.model.withdraw.Withdraw;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WithdrawResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Withdraw withdraw;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    public WithdrawResponse(final String error) {
        this.error = error;
    }
}
