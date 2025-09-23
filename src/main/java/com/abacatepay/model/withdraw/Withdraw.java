package com.abacatepay.model.withdraw;

import java.time.LocalDateTime;

import com.abacatepay.model.withdraw.enums.StatusWithdraw;
import com.abacatepay.model.withdraw.enums.WithdrawKind;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Withdraw {
    private String id;
    private StatusWithdraw status;
    private boolean devMode;
    private String receiptUrl;
    private WithdrawKind kind;
    private Double amount;
    private Integer platformFee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String externalId;
}
