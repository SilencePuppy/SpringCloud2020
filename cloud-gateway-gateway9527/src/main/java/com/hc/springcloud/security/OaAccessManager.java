package com.hc.springcloud.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * @author 李晓冰
 * @date 2020年11月12日
 */
public class OaAccessManager implements ReactiveAuthorizationManager<AuthorizationContext>{

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext context) {
        ServerWebExchange exchange = context.getExchange();
        String requestPath = exchange.getRequest().getURI().getPath();
        return authentication.map(auth -> {
            return new AuthorizationDecision(checkAuthorities(exchange,auth,requestPath));
        });
    }

    private boolean checkAuthorities(ServerWebExchange exchange, Authentication auth, String requestPath) {
        if (!(auth instanceof UsernamePasswordAuthenticationToken)||!auth.isAuthenticated()) {
            return false;
        }

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) auth;
        UserDetails userDetails = (UserDetails) authenticationToken.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            AntPathMatcher matcher =new AntPathMatcher();
            if (matcher.match(authority.getAuthority(),requestPath)) {
                return true;
            }
        }
        return false;
    }
}
