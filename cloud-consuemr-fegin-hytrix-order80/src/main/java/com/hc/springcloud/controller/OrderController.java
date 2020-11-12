package com.hc.springcloud.controller;

import com.hc.springcloud.service.PaymentCallerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李晓冰
 * @date 2020年11月03日
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private PaymentCallerService paymentCallerService;


    @GetMapping("/consumer/payment/info/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id){
        return paymentCallerService.paymentInfoOk(id);
    }

    @GetMapping("/consumer/payment/info/timeout/{id}}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        return paymentCallerService.paymentInfoTimeout(id);
    }

}
