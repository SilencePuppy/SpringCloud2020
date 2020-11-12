package com.hc.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author 李晓冰
 * @date 2020年11月03日
 */
@Component
public class PaymentFallbackService implements PaymentCallerService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "哭了";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return null;
    }
}
