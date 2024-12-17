package com.abacatepay.Models.Client.exceptions;

import feign.FeignException;
import feign.Request;

public class FeignCustomException extends FeignException {
    public FeignCustomException(Integer status,String message,Request request) {
        super(status, message, request);
    }
}
