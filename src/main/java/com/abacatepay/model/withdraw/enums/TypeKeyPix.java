package com.abacatepay.model.withdraw.enums;

public enum TypeKeyPix {
    CPF("CPF"),
    CNPJ("CNPJ"),
    PHONE("PHONE"),
    EMAIL("EMAIL"),
    RANDOM("RANDOM");

    private final String valueMethod;

    TypeKeyPix(final String valueMethod) {
        this.valueMethod = valueMethod;
    }

    public String getValueMethod() {
        return valueMethod;
    }
}
