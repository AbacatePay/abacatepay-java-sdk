package com.abacatepay.model;

import com.abacatepay.services.IAbacatePayBilling;
import com.abacatepay.services.IAbacatePayCustomer;

public interface IAbacatePay {
    IAbacatePayBilling billing();
    IAbacatePayCustomer customer();
}
