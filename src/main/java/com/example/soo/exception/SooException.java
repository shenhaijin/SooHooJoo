package com.example.soo.exception;

import com.example.common.code.error.ExceptionCode;

/**
 * @author shenhaijin
 * @date 2020/12/21 13:51
 * @description 业务系统异常
 * @version 1.0
 **/
public class SooException extends RuntimeException {
    public SooException(Object object){
        super(object.toString());
    }
    public SooException(){
        super();
    }
    protected ExceptionCode exceptionCode;
    protected String exceptionMessage;

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
