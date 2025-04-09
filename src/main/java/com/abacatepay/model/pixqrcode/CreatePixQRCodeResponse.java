package com.abacatepay.model.pixqrcode;

import com.abacatepay.model.billing.BillingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePixQRCodeResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CreatePixQRCodeResponseData data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object error;

    public CreatePixQRCodeResponse(Object error){
        this.error = error;
    }

    public CreatePixQRCodeResponse(){
    }
}

@Builder
class CreatePixQRCodeResponseData{
    private String id;
    private BigDecimal amount;
    private BillingStatus status;
    private boolean devMode;
    private String brCode;
    private String brCodeBase64;
    private String platformFee;
    @JsonFormat(timezone = "America/Sao_Paulo", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;
    @JsonFormat(timezone = "America/Sao_Paulo", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedAt;
    @JsonFormat(timezone = "America/Sao_Paulo", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime expiresAt;
}
