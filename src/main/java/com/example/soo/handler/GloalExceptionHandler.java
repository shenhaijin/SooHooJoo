//package com.example.soo.handler;
//
//import com.example.common.code.error.ExceptionCode;
//import com.example.common.response.Result;
//import com.example.common.response.ResultFactory;
//import com.example.soo.exception.SooException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///**
// * @Author shenhaijin
// * @Date 2020/12/21 13:49
// * @Description 全局异常处理类
// * @Version 1.0
// **/
//@RestControllerAdvice
//public class GloalExceptionHandler {
//    private static final Logger logger = LoggerFactory.getLogger(GloalExceptionHandler.class);
//    @ExceptionHandler(value = Exception.class)
//    public Result handlerException(Exception exception){
//        Result failResult = null;
//        if(exception instanceof SooException){
//            SooException sooException = (SooException) exception;
//            failResult =
//                    ResultFactory.fail(sooException.getExceptionCode().getExceCode(),sooException.getExceptionMessage());
//        }else{
//            failResult =
//                    ResultFactory.fail(ExceptionCode.SYSTEM.getExceCode(),exception.getMessage());
//        }
//        logger.error(exception.getMessage());
//        return failResult;
//    }
//
//    @ExceptionHandler(value = Throwable.class)
//    public Result handlerThrowable(Throwable ex){
//        Result failResult = ResultFactory.fail(ExceptionCode.SYSTEM.getExceCode(),ex.getMessage());
//        logger.error(ex.getMessage());
//        return failResult;
//    }
//}
