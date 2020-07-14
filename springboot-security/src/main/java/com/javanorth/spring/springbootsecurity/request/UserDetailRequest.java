package com.javanorth.spring.springbootsecurity.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDetailRequest {
    private String username;
    private String password;
}
