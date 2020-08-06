package com.javanorth.spring.springbootshiro.service;

public interface UserDetailService {

    /**
     * user register
     * @param username
     * @param password
     * @return
     */
    boolean userRegister(String username, String password);

    /**
     * user login
     * @param username
     * @param password
     * @return
     */
    boolean userLogin(String username, String password);
}
