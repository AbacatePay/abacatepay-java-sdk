package com.abacatepay.model.withdraw.enums;

public enum StatusWithdraw {
    PENDING("PENDING"),
    EXPIRED("EXPIRED"),
    CANCELLED("CANCELLED"),
    COMPLETE("COMPLETE"),
    REFUNDED("REFUNDED");

    private final String valueMethod;

    StatusWithdraw(final String valueMethod) {
        this.valueMethod = valueMethod;
    }

    public String getValueMethod() {
        return valueMethod;
    }
}
