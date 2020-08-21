package com.javanorth.spring.springbootjapidocs.response;

public enum ResponseResult {
    /**
     * 通用的请求返回枚举，所有请求的返回code为字符串，前5位为字符串，后5位位指定的数字。
     * 前5位通常指多个服务中的某个服务的简称，表示该响应是某个服务返回的。
     * 后5位通常表示响应码，为自定义响应码。
     * e.g.
     * S10000,S10001,S10002等
     * 建议对每个应用进行分类
     */

    // e.g.

    LOGIN_FAILED("S10000", "User Login Failed"),
    SEND_MAIL_FAILED("S10001", "Send Mail Failed"),
    USER_EXIST("S10002", "Username Already Exist, Please Use Another"),
    USER_NOT_EXIST("S10003", "Username Not Exist"),
    PWD_ERROR("S10004", "Password Error"),
    AUTH_ERROR("S10005", "User Authenticate Error"),

    REQUEST_SUCCESS("S20000", "Success"),
    LOGIN_SUCCESS("S20001", "Login Success"),
    AUTH_SUCCESS("S20002", "User Authenticate Success"),
    REQUEST_FAILED("S40000", "Request Failed"),
    AUTH_FAILED("S40001", "User Auth Failed, Please Login With Username And Password")

    ;
    private final String code;
    private final String message;

    ResponseResult(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }
}
