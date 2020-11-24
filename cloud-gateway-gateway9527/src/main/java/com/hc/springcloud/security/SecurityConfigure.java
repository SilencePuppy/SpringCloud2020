package com.hc.springcloud.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author 李晓冰
 * @date 2020年11月12日
 */
@Configuration
public class SecurityConfigure implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        OaSecurityContextRepository securityContextRepository = this.applicationContext.getBean(OaSecurityContextRepository.class);
        SecurityWebFilterChain build = http.securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .anyExchange().access(new OaAccessManager())
                .and()
                .formLogin()
                .securityContextRepository(securityContextRepository)
                .and()
                .build();

        return build;
    }

    @Bean
    public OaSecurityContextRepository securityContextRepository() {
        return new OaSecurityContextRepository();
    }

    @Bean
    public ReactiveUserDetailsService reactiveUserDetailsService() {
        return new OaUserDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword != null && encodedPassword.contentEquals(rawPassword);
            }
        };
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
