package com.hc.springcloud.controller;

import com.hc.springcloud.entities.CommonResult;
import com.hc.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李晓冰
 * @date 2020年11月02日
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/consumer/payment")
    public String getPayment() {
        return restTemplate.getForObject(PAYMENT_URL+"/payment/eureka/",String.class);
    }
}
