package com.abacatepay.Models.AbacatePayClient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbacatePayClientResponse {
    private String id;
    private MetaData metaData;
}
