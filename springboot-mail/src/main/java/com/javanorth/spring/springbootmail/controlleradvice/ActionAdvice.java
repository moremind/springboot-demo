package com.javanorth.spring.springbootmail.controlleradvice;

import com.javanorth.spring.springbootmail.exception.SendMailException;

import com.javanorth.spring.springbootmail.response.ResponseResult;
import com.javanorth.spring.springbootmail.response.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class ActionAdvice {

    @ExceptionHandler(SendMailException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil handleException(HttpServletRequest request, Exception e) {
        return ResponseUtil.fail(ResponseResult.SEND_MAIL_FAILED.getCode(), ResponseResult.SEND_MAIL_FAILED.getMsg(),
                request.getRequestURI());
    }
}
