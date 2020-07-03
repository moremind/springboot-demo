package com.javanorth.spring.springbootredis.exampletwo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ResultUtils<T> {
    private String code;
    private String msg;
    private T data;

    public ResultUtils(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultUtils(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultUtils success() {
        return new ResultUtils(Result.SUCCESS.getCode(), Result.SUCCESS.getMsg());
    }

    public static ResultUtils success(Object data) {
        return new ResultUtils<>(Result.SUCCESS.getCode(), Result.SUCCESS.getMsg(), data);
    }

    public static ResultUtils success(String code, String msg) {
        return new ResultUtils(code, msg);
    }

    public static ResultUtils success(String code, String msg, Object data) {
        return new ResultUtils<>(code, msg, data);
    }

    public static ResultUtils error() {
        return new ResultUtils(Result.ERROR.getCode(), Result.ERROR.getMsg());
    }

    public static ResultUtils error(Object data) {
        return new ResultUtils<>(Result.ERROR.getCode(), Result.ERROR.getMsg(), data);
    }

    public static ResultUtils error(String code, String msg) {
        return new ResultUtils(code, msg);
    }

    public static ResultUtils error(String code, String msg, Object data) {
        return new ResultUtils<>(code, msg, data);
    }

}
