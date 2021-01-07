package com.example.soo.exception;

import com.example.common.code.error.ExceptionCode;

/**
 * @Author shenhaijin
 * @Date 2021/1/4 14:21
 * @Description TODO
 * @Version 1.0
 **/
public class ParamException extends SooException {
    public ParamException(String exceptionMessage) {
        this.exceptionCode = ExceptionCode.PARAM;
        this.exceptionMessage = exceptionMessage;
    }
}
