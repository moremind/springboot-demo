package cn.moremind.spring.springbootredis.exampletwo.exception;

/**
 * 自定义
 */
public class ServiceException extends RuntimeException {
    private String retCode;
    private String msg;


    public ServiceException(String retCode) {
        this.retCode = retCode;
    }

    public ServiceException(String retCode, String msg) {
        this.retCode = retCode;
        this.msg = msg;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String retCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
