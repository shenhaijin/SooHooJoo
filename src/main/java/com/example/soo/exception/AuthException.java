package com.example.soo.exception;

import com.example.common.code.error.ExceptionCode;

/**
 * @Author shenhaijin
 * @Date 2021/1/4 14:16
 * @Description TODO
 * @Version 1.0
 **/
public class AuthException extends SooException{
    public AuthException(String exceptionMessage) {
        this.exceptionCode = ExceptionCode.AUTH;
        this.exceptionMessage = exceptionMessage;
    }
}
