package com.xupt.house.enums;

/**
 * 房屋状态enum
 */
public enum PostStatusEnum {

    /**
     * 未租出
     */
    ON_SALE(0),

    /**
     * 已出租
     */
    OFF_SALE(1),

    /**
     * 已删除
     */
    RECYCLE(2);

    private Integer code;

    PostStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}