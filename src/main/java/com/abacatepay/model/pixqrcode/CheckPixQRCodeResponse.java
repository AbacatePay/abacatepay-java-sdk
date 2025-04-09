package com.abacatepay.model.pixqrcode;

import com.abacatepay.model.billing.BillingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckPixQRCodeResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CheckPixQRCodeResponseData data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object error;

    public CheckPixQRCodeResponse (Object error){
        this.error = error;
    }
    public CheckPixQRCodeResponse (){
    }
}

@Builder
class CheckPixQRCodeResponseData{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BillingStatus status;

    @JsonFormat(timezone = "America/Sao_Paulo", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime expiredAt;
}
