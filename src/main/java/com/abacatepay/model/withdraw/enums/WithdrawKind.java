package com.abacatepay.model.withdraw.enums;

public enum WithdrawKind {
    PAYMENT("PAYMENT"),
    WITHDRAW("WITHDRAW");

    private final String valueMethod;

    WithdrawKind(final String valueMethod) {
        this.valueMethod = valueMethod;
    }

    public String getValueMethod() {
        return valueMethod;
    }
}
