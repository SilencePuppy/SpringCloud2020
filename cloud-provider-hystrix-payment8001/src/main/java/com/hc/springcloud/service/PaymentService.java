package com.hc.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.tomcat.jni.Error;
import org.springframework.stereotype.Service;

import javax.crypto.SealedObject;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 李晓冰
 * @date 2020年11月03日
 */
@Service
public class PaymentService {

    public String paymentInfoOk(Integer id){
        return  "线程池："+Thread.currentThread().getName()+" paymentInfoOk Id:"+id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfoTimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  "线程池："+Thread.currentThread().getName()+" paymentInfoTimeout Id:"+id;
    }



    public String paymentInfoTimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfoTimeoutHandler Id:"+id;
    }

    /**********************熔断*************************/

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "20")
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0) {
            throw new RuntimeException("id <0");
        }

        String serialNum = UUID.randomUUID().toString();

        return  "线程池："+Thread.currentThread().getName()+" paymentInfoTimeout Id:"+id +" uuid:"+ serialNum;
    }

    public String paymentCircuitBreakerHandler(Integer id){
        return "id 不能为负数";
    }

}
