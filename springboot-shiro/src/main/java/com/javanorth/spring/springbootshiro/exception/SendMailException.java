package com.javanorth.spring.springbootshiro.exception;

public class SendMailException extends RuntimeException {
    private String retCode;
    private String msg;

    public SendMailException() {
    }
    public SendMailException(String retCode, String msg) {
        this.retCode = retCode;
        this.msg = msg;
    }

    public SendMailException(String message, String retCode, String msg) {
        super(message);
        this.retCode = retCode;
        this.msg = msg;
    }

    public SendMailException(String message, Throwable cause, String retCode, String msg) {
        super(message, cause);
        this.retCode = retCode;
        this.msg = msg;
    }

    public SendMailException(Throwable cause, String retCode, String msg) {
        super(cause);
        this.retCode = retCode;
        this.msg = msg;
    }

    public SendMailException(String message, Throwable cause, boolean enableSuppression,
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
