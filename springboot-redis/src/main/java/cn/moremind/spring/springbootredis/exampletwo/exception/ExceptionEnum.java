package cn.moremind.spring.springbootredis.exampletwo.exception;

public enum  ExceptionEnum {

    HEADER_NO_TOKEN("A10000", "HEADER_NO_TOKEN"),
    TOKEN_NOT_EXIST("A10001", "TOKEN_NOT_EXIST"),
    DELETE_KEY_FAILED("A10002", "DELETE_KEY_FAILED");


    private final String code;
    private final String msg;

    ExceptionEnum(String code, String msg) {
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
