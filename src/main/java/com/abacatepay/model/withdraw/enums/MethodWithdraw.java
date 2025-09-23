package com.abacatepay.model.withdraw.enums;

public enum MethodWithdraw {
    PIX("PIX");

    private final String valueMethod;

    MethodWithdraw(final String valueMethod) {
        this.valueMethod = valueMethod;
    }

    public String getValueMethod() {
        return valueMethod;
    }
}
