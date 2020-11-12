package com.hc.springcloud.contorller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李晓冰
 * @date 2020年11月02日
 */
@RestController
@Slf4j
public class OrderConsulController {

    public static final String INVOKE_URL="http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/consul/")
    public String paymentInfo(){
        String ret =restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        return ret;
    }

}
