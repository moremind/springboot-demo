package com.javanorth.spring.springbootshiro.controlleradvice;


import com.javanorth.spring.springbootshiro.exception.PasswordErrorException;
import com.javanorth.spring.springbootshiro.exception.UserAlreadyExistException;
import com.javanorth.spring.springbootshiro.exception.UserNotExistException;
import com.javanorth.spring.springbootshiro.response.ResponseResult;
import com.javanorth.spring.springbootshiro.response.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class ActionAdvice {

    /**
     * handle user exist
     * return response of ResponseUtil
     * @see ResponseUtil
     */
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil handleException(HttpServletRequest request, Exception e) {
        return ResponseUtil.fail(ResponseResult.USER_EXIST.getCode(), ResponseResult.USER_EXIST.getMsg(),
                request.getRequestURI());
    }


    /**
     * handle user not exist
     * return response of ResponseUtil
     * @see ResponseUtil
     */
    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil handleUserNotExistException(HttpServletRequest request, Exception e) {
        return ResponseUtil.fail(ResponseResult.USER_NOT_EXIST.getCode(), ResponseResult.USER_NOT_EXIST.getMsg(),
                request.getRequestURI());
    }

    /**
     * handle user password error
     * return response of ResponseUtil
     * @see ResponseUtil
     */
    @ExceptionHandler(PasswordErrorException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil handlePasswordErrorException(HttpServletRequest request, Exception e) {
        return ResponseUtil.fail(ResponseResult.PWD_ERROR.getCode(), ResponseResult.PWD_ERROR.getMsg(),
                request.getRequestURI());
    }
}
