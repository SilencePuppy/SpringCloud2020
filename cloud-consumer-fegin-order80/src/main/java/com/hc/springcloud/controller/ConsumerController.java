package com.hc.springcloud.controller;

import com.hc.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李晓冰
 * @date 2020年11月03日
 */
@RestController
public class ConsumerController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/order")
    public String getPayment(){
        return paymentFeignService.getPaymentById();
    }
}
