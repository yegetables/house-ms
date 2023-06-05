package com.xupt.house.enums;

public enum TrueFalseEnum {

    /**
     * 真
     */
    TRUE("true"),

    /**
     * 假
     */
    FALSE("false");

    private String value;

    TrueFalseEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}