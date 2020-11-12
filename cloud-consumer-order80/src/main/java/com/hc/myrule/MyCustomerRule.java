package com.hc.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置ribbon负载均衡策略
 * @author 李晓冰
 * @date 2020年11月03日
 */
@Configuration
public class MyCustomerRule {
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
