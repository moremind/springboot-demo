package com.javanorth.spring.springbootshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<String> roles;
    /**
     * user permission
     */
    private List<String> permissions;

}
