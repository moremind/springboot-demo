package com.javanorth.spring.springbootinit.response;

public class ResponseUtil<T> {
    private String code;
    private String message;
    private T data;

    public ResponseUtil(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseUtil(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * Builder模式
     */
    public ResponseUtil(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = (T) builder.data;
    }

    public static final class Builder {
        private String code;
        private String message;
        private Object data;

        public Builder() {
        }

        public Builder(String code, String msg) {
            this.code = code;
            this.message = msg;
        }

        public Builder(String code, String msg, Object data) {
            this.code = code;
            this.message = msg;
            this.data = data;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String msg) {
            this.message = msg;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public ResponseUtil build() {
            // 需要检查线程安全
            return new ResponseUtil(this);
        }
    }

    /**
     * 使用默认成功响应
     * @return 响应状态
     */
    public static ResponseUtil success() {
//        return new ResponseUtil(ResponseResult.REQUEST_SUCCESS.getCode(), ResponseResult.REQUEST_SUCCESS.getMsg());

        return new ResponseUtil.Builder().code(ResponseResult.REQUEST_SUCCESS.getCode())
                .message(ResponseResult.REQUEST_SUCCESS.getMsg())
                .build();
    }

    /**
     * 使用默认成功且携带响应数据
     * @param data 响应数据
     * @return 响应状态
     */
    public static ResponseUtil success(Object data) {
        return new ResponseUtil.Builder().code(ResponseResult.REQUEST_SUCCESS.getCode())
                .message(ResponseResult.REQUEST_SUCCESS.getMsg())
                .data(data)
                .build();
    }

    /**
     * 响应成功且自定响应状态码、响应消息
     * @param code 响应码
     * @param msg 响应消息
     * @return 响应状态
     */
    public static ResponseUtil success(String code, String msg) {
        return new ResponseUtil.Builder().code(code)
                .message(msg)
                .build();
    }

    /**
     * 响应成功且自定义响应状态码、响应消息、响应数据
     * @param code 响应码
     * @param msg 响应消息
     * @param data 响应数据
     * @return 响应状态
     */
    public static ResponseUtil success(String code, String msg, Object data) {
        return new ResponseUtil.Builder().code(code)
                .message(msg)
                .data(data)
                .build();
    }

    /**
     * 使用默认失败响应
     * @return 响应状态
     */
    public static ResponseUtil error() {
        return new ResponseUtil.Builder().code(ResponseResult.REQUEST_FAILED.getCode())
                .message(ResponseResult.REQUEST_FAILED.getMsg())
                .build();
    }

    /**
     * 使用默认成功且携带响应数据
     * @param data 响应数据
     * @return 响应状态
     */
    public static ResponseUtil error(Object data) {
        return new ResponseUtil.Builder().code(ResponseResult.REQUEST_FAILED.getCode())
                .message(ResponseResult.REQUEST_FAILED.getMsg())
                .data(data)
                .build();
    }

    /**
     * 响应失败且自定响应状态码、响应消息
     * @param code 响应码
     * @param msg 响应消息
     * @return 响应状态
     */
    public static ResponseUtil error(String code, String msg) {
        return new ResponseUtil.Builder().code(code)
                .message(msg)
                .build();
    }

    /**
     * 响应失败且自定义响应状态码、响应消息、响应数据
     * @param code 响应码
     * @param msg 响应消息
     * @param data 响应数据
     * @return 响应状态
     */
    public static ResponseUtil error(String code, String msg, Object data) {
        return new ResponseUtil.Builder().code(code)
                .message(msg)
                .data(data)
                .build();
    }

    @Override
    public String toString() {
        return "ResponseUtil{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
