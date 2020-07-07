package com.javanorth.spring.springbootinit.exception;

/**
 * Service层的异常
 */
public class ServiceException extends RuntimeException {
    private String retCode;
    private String msg;

    public ServiceException() {
    }
    public ServiceException(String retCode, String msg) {
        this.retCode = retCode;
        this.msg = msg;
    }

    public ServiceException(String message, String retCode, String msg) {
        super(message);
        this.retCode = retCode;
        this.msg = msg;
    }

    public ServiceException(String message, Throwable cause, String retCode, String msg) {
        super(message, cause);
        this.retCode = retCode;
        this.msg = msg;
    }

    public ServiceException(Throwable cause, String retCode, String msg) {
        super(cause);
        this.retCode = retCode;
        this.msg = msg;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression,
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
