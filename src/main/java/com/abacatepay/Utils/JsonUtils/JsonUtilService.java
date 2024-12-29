package com.abacatepay.Utils.JsonUtils;

import com.abacatepay.Models.AbacatePayClient.AbacatePayClientRequest;
import com.abacatepay.Models.AbacatePayClient.AbacatePayClientResponse;
import com.abacatepay.Models.Billing.Billing;
import com.abacatepay.Models.Billing.CreateBillingData;
import com.abacatepay.Models.Billing.CreateBillingResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface JsonUtilService {
    AbacatePayClientResponse abacatePayClientResponseFromJsonToObject(String string);

    JSONObject createAbacatePayClientJson(AbacatePayClientRequest request);

    List<AbacatePayClientResponse> abacatePayClientResponseFromJsonArrayToObject(String string);

    JSONObject createBillingDataJson(CreateBillingData billingData);

    Billing createBillingResponseFromJsonToObject(String string);

    List<Billing> listingBillingResponseFromJsonToObject(String string);
}
