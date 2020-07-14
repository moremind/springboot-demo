package com.javanorth.spring.springbootsecurity.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@ToString
@Builder
public class Role implements GrantedAuthority {

    private int id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
