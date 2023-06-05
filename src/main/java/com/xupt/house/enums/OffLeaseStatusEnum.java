package com.xupt.house.enums;

/**
 *  退租状态
 *      -1驳回，0待审核，1通过
 */
public enum OffLeaseStatusEnum {


    /**
     * 退租审核驳回
     */
    OFF_LEASE_APPLY_REJECT(-1),

    /**
     * 退租申请待审核
     */
    OFF_LEASE_APPLY(0),

    /**
     * 退租审核通过，可以结算退款了
     */
    OFF_LEASE_APPLY_PASS(1);


    private Integer code;

    OffLeaseStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}