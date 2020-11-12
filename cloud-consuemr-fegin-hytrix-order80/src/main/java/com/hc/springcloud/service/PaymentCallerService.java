package com.hc.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 李晓冰
 * @date 2020年11月03日
 */
@Service
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMNET",fallback = PaymentFallbackService.class)
public interface PaymentCallerService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfoOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfoTimeout(@PathVariable("id") Integer id);
}
