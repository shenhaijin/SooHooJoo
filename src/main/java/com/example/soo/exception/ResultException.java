package com.example.soo.exception;

import com.example.common.code.error.ExceptionCode;

/**
 * @Author shenhaijin
 * @Date 2021/1/4 14:23
 * @Description TODO
 * @Version 1.0
 **/
public class ResultException extends SooException{
    public ResultException(String exceptionMessage){
        this.exceptionCode = ExceptionCode.RESULT;
        this.exceptionMessage = exceptionMessage;
    }
}
