package com.spy.mall.common.enums;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 *
 * @Author: spy
 * @Date: 2021/5/15 7:04
 */

@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(2012, "服务异常"),

    PAY_RUN(205, "支付中"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限"),

    SEC_KILL_NO_START(210, "秒杀还没开始"),
    SEC_KILL_RUN(211, "正在排队中"),
    SEC_KILL_NO_PAY_ORDER(212, "您有未支付的订单"),
    SEC_KILL_FINISH(213, "已售罄"),
    SEC_KILL_END(214, "秒杀已结束"),
    SEC_KILL_SUCCESS(215, "抢单成功"),
    SEC_KILL_FAIL(216, "抢单失败"),
    SEC_KILL_ILLEGAL(217, "请求不合法"),
    SEC_KILL_ORDER_SUCCESS(218, "下单成功"),
    ;

    private Integer code;

    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

