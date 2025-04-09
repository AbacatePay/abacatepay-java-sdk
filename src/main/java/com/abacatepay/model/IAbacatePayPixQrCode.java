package com.abacatepay.model;

import com.abacatepay.model.pixqrcode.CheckPixQRCodeResponse;
import com.abacatepay.model.pixqrcode.CreatePixQRCodeData;
import com.abacatepay.model.pixqrcode.CreatePixQRCodeResponse;

public interface IAbacatePayPixQrCode {
     CreatePixQRCodeResponse create(CreatePixQRCodeData pixQRCode);
     CheckPixQRCodeResponse check(String id);
}
