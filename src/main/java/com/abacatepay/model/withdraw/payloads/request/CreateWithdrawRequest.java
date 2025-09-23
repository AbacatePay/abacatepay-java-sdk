package com.abacatepay.model.withdraw.payloads.request;

import com.abacatepay.model.withdraw.enums.MethodWithdraw;
import com.abacatepay.model.withdraw.enums.TypeKeyPix;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWithdrawRequest {
    @JsonProperty(required = true)
    private String externalId;
    @JsonProperty(required = true)
    private MethodWithdraw method;
    @JsonProperty(required = true)
    private Double amount;
    @JsonProperty(required = false)
    private String description;
    @JsonProperty(required = true)
    private Pix pix;

    @Builder
    @Data
    static class Pix {
        private TypeKeyPix type;
        private String key;
    }
}
