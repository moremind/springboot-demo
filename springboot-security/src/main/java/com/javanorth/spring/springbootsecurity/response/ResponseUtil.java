package com.javanorth.spring.springbootsecurity.response;

public class ResponseUtil<T> {
    private String code;
    private String path;
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

    public ResponseUtil(String code, String method, String path, String message, T data) {
        this.code = code;
        this.path = path;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
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
        this.path = builder.path;
        this.message = builder.message;
        this.data = (T) builder.data;
    }

    public static final class Builder {
        private String code;
        private String path;
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

        public Builder(String code, String message, Object data, String method, String path) {
            this.code = code;
            this.message = message;
            this.data = data;
            this.path = path;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
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
     * @param path 路径
     * @param data 数据
     * @return 响应状态
     */
    public static ResponseUtil success(String path, Object... data) {
        return new ResponseUtil.Builder().code(ResponseResult.REQUEST_SUCCESS.getCode())
                .path(path)
                .message(ResponseResult.REQUEST_SUCCESS.getMsg())
                .data(data)
                .build();
    }

    /**
     * 响应成功且自定义响应状态码、响应消息、响应数据
     * @param code 响应码
     * @param msg 响应消息
     * @param data 响应数据
     * @return 响应状态
     */
    public static ResponseUtil success(String code, String msg, String path, Object... data) {
        return new ResponseUtil.Builder().code(code)
                .path(path)
                .message(msg)
                .data(data)
                .build();
    }

    /**
     * 默认错误返回
     * @param path 请求路径
     * @param data 参数
     * @return 响应状态
     */
    public static ResponseUtil error(String path, Object... data) {
        return new ResponseUtil.Builder().code(ResponseResult.REQUEST_FAILED.getCode())
                .path(path)
                .message(ResponseResult.REQUEST_FAILED.getMsg())
                .data(data)
                .build();
    }

    /**
     *
     * @param code 响应码
     * @param msg 响应消息
     * @param path 路径
     * @param data 数据
     * @return 响应状态
     */
    public static ResponseUtil error(String code, String msg, String path, Object... data) {
        return new ResponseUtil.Builder().code(code)
                .path(path)
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
