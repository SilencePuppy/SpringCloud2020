package com.hc.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 李晓冰
 * @date 2020年11月03日
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    public String  serverPort;


    @RequestMapping("/payment/consul")
    public String paymentConsul(){
        return "springcluod with consul:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
