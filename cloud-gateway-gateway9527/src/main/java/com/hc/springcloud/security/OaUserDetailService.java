package com.hc.springcloud.security;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 李晓冰
 * @date 2020年11月12日
 */
public class OaUserDetailService implements ReactiveUserDetailsService {

    public static Map<String, User> userMap = new ConcurrentHashMap<>();

    public OaUserDetailService() {
        List authorities = new ArrayList();
        authorities.add(new OaGrantedAuthority("/payment/eureka/**"));
        userMap.put("li", new User("li", "123", authorities));

        authorities = new ArrayList();
        authorities.add(new OaGrantedAuthority("/payment/eureka/**"));
        authorities.add(new OaGrantedAuthority("/payment/discovery/**"));
        userMap.put("hong", new User("hong", "123", authorities));
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        if (username == null) {
            return Mono.empty();
        }
        User user = userMap.get(username);
        return user == null ? Mono.empty() : Mono.just(user);
    }
}
