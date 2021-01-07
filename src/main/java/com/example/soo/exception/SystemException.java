package com.example.soo.exception;

import com.example.common.code.error.ExceptionCode;

/**
 * @Author shenhaijin
 * @Date 2020/12/21 13:54
 * @Description TODO
 * @Version 1.0
 **/
public class SystemException extends SooException {
    public SystemException(String exceptionMessage) {
        this.exceptionCode = ExceptionCode.SYSTEM;
        this.exceptionMessage = exceptionMessage;
    }
}
