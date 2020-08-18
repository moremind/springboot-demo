package com.javanorth.spring.springbootshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetail {
    /**
     * unique code
     */
    private String uid;
    /**
     * username
     */
    private String username;

    /**
     * pwd with md5
     */
    private String password;

    /**
     * user salt
     */
    private String salt;

    /**
     * user roles
     */
    private Set<String> roles;
    /**
     * user permission
     */
    private Set<String> permissions;

}
