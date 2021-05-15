package com.spy.mall.payment.svc.controller;

import com.spy.mall.payment.api.AliPaymentApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: spy
 * @Date: 2021/5/15 12:08
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements AliPaymentApi {

    /**
     * 去支付宝支付页面
     *
     * @param orderId
     * @return
     */
    @Override
    @GetMapping("/submit/{orderId}")
    public String submit(@PathVariable(value = "orderId") Long orderId) {
        return null;
    }
}
