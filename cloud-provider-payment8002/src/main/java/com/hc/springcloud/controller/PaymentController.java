package com.hc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 李晓冰
 * @date 2020年11月02日
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/eureka/")
    public String getPaymentById(){
        return "springcluod with eureka:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
