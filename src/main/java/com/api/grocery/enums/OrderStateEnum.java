package com.api.grocery.enums;

public enum OrderStateEnum {

    OPENED("OPENED"),
	CLOSED("CLOSED");

    private final String value;

    OrderStateEnum(String value) {
        this.value = value;
	}

    public String getValue() {
        return value;
    }
}
