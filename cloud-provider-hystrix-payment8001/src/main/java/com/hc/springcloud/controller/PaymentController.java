package com.hc.springcloud.controller;

import com.hc.springcloud.service.PaymentService;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李晓冰
 * @date 2020年11月03日
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable Integer id) {
        String result = paymentService.paymentInfoOk(id);
        log.info(result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable Integer id) {
        String result = paymentService.paymentInfoTimeOut(id);
        log.info(result);
        return result;
    }

    @GetMapping("/payment/circuit/breaker/{id}")
    public String paymentCircuitBreaker(@PathVariable Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info(result);
        return result;
    }
}
