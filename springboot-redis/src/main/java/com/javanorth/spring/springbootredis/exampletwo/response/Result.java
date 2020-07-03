package com.javanorth.spring.springbootredis.exampletwo.response;

public enum Result {
    SUCCESS("0", "success"),
    ERROR("-1", "failed");
    private final String code;
    private final String msg;

    Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
