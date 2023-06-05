package com.xupt.house.enums;

/**
 *角色枚举
 */
public enum RoleEnum {

    /**
     * 管理员
     */
    ADMIN("admin"),

    /**
     * 房东
     */
    OWNER("owner"),

    /**
     * 租客
     */
    CUSTOMER("customer"),

    /**
     * 中介
     */
    AGENCY("agency");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}