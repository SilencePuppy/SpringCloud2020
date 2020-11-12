package com.hc.springcloud.security;

import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 李晓冰
 * @date 2020年11月12日
 */
public class OaSecurityContextRepository implements ServerSecurityContextRepository {

    public static final String OA_TOKEN_NAM = "oa_token";

    Map<String, UserDetails> userMap = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        ServerHttpResponse response = exchange.getResponse();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        String token = UUID.randomUUID().toString();
        response.addCookie(ResponseCookie.from(OA_TOKEN_NAM,token).build());
        userMap.put(token,userDetails);
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();

        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        if (!cookies.containsKey(OA_TOKEN_NAM)) {
           return Mono.empty();
        }
        HttpCookie httpCookie = cookies.get(OA_TOKEN_NAM).get(0);
        UserDetails userDetails = userMap.get(httpCookie.getValue());
        if (userDetails == null) {
            return Mono.empty();
        }

        UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextImpl securityContext =new SecurityContextImpl(token);
        return Mono.just(securityContext);
    }
}
