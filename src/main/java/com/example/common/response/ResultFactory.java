package com.example.common.response;

import com.example.common.code.error.ExceptionCode;

/**
 * @Author shenhaijin
 * @Date 2020/12/22 14:04
 * @Description 请求返回结果对象
 * @Version 1.0
 **/
public class ResultFactory {
    private ResultFactory(){}
    public static <T> Result<T> success(T data){
        Result<T> successResult = new Result();
        successResult.setData(data);
        successResult.setCode(ExceptionCode.SUCCESS.getExceCode());
        return successResult;
    }

    public static Result fail(String exceCode,String message){
        Result failResult = new Result();
        failResult.setCode(exceCode);
        failResult.setMessage(message);
        return failResult;
    }
}
