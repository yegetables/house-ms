package com.xupt.house.enums;

/**
 * 房屋类型enum
 */
public enum PostTypeEnum {

    /**
     * 房屋
     */
    POST_TYPE_POST("post"),

    /**
     * 页面
     */
    POST_TYPE_PAGE("page"),

    /**
     * 公告
     */
    POST_TYPE_NOTICE("notice");

    private String value;

    PostTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}