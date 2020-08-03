package com.javanorth.spring.springbootshiro.exception;

public enum ExceptionEnum {
    /**
     * 所有自定义异常枚举长度10位，前5位表示某个服务或者应用的标识，末尾5位为数字
     * 1.调用内部服务返回的异常retCode以ISE开头，表示InnerServerException，
     * 2.调用外部服务返回的异常retCode以OSE开头，表示OuterServerException
     * 3.调用数据库操作的返回的异常retCode以DBSE开头，表示DatabaseServerException
     * 4.调用中间件返回的异常MSE开头，表示MiddlewareSeverException
     * 5.
     */
    // e.g.
    // 1.调用内部Service，例如:Controller调用Service返回Exception
    CALL_SERVICE_EXCEPTION("ISE10001", "Call Service Error"),

    // 2.调用外部HTTP接口，例如:本地Service调用某个外部Login的HTTP接口返回Exception
    CALL_HTTP_LOGIN_EXCEPTION("OSE10001", "Call Http Login Error"),

    // 3.调用数据库操作返回异常，例如:本地服务调用MySQL插入操作返回Exception
    INSERT_SQL_EXCEPTION("DBSE10001", "Insert SQL Error"),

    // 4.使用中间件服务时返回异常，例如：本地服务调用MQ中间件返回Exception
    MQ_INITIAL_EXCEPTION("MSE10001", "MQ Initial Error"),

    SEND_MAIL_EXCEPTION("ISE10002", "Send Mail Error"),

    // 略
    ;


    private final String retCode;
    private final String message;

    ExceptionEnum(String retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public String getRetCode() {
        return retCode;
    }

    public String getMessage() {
        return message;
    }
}
