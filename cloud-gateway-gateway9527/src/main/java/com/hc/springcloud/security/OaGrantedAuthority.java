package com.hc.springcloud.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author 李晓冰
 * @date 2020年11月12日
 */
public class OaGrantedAuthority implements GrantedAuthority {
    private String authority;

    public OaGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
