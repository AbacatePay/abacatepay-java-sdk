package com.abacatepay.Models.AbacatePayClient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.RequestBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbacatePayClientRequest {
    private String name;
    private String cellphone;
    private String email;
    private String taxId;
}
