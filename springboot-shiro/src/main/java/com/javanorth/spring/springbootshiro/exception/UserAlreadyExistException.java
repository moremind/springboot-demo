package com.javanorth.spring.springbootshiro.exception;

/**
 * Service层的异常
 */
public class UserAlreadyExistException extends RuntimeException {
    private String retCode;
    private String msg;

    public UserAlreadyExistException() {
    }
    public UserAlreadyExistException(String retCode, String msg) {
        this.retCode = retCode;
        this.msg = msg;
    }

    public UserAlreadyExistException(String message, String retCode, String msg) {
        super(message);
        this.retCode = retCode;
        this.msg = msg;
    }

    public UserAlreadyExistException(String message, Throwable cause, String retCode, String msg) {
        super(message, cause);
        this.retCode = retCode;
        this.msg = msg;
    }

    public UserAlreadyExistException(Throwable cause, String retCode, String msg) {
        super(cause);
        this.retCode = retCode;
        this.msg = msg;
    }

    public UserAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace, String retCode, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.retCode = retCode;
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public String getMsg() {
        return msg;
    }
}
