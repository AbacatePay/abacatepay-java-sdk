package com.abacatepay.model.withdraw.payloads.response;

import java.util.List;

import com.abacatepay.model.withdraw.Withdraw;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListWithdrawResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Withdraw> withdrawals;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    public ListWithdrawResponse(final String error) {
        this.error = error;
    }
}
