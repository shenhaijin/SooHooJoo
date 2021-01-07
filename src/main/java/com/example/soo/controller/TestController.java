package com.example.soo.controller;

import com.example.common.base.DateQueryBase;
import com.example.common.response.Result;
import com.example.common.response.ResultFactory;
import com.example.soo.aop.CtrlAop;
import com.example.soo.exception.ParamException;
import com.example.soo.exception.SystemException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author shenhaijin
 * @Date 2020/12/21 14:44
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("test")
@Api(tags = "测试接口")
@ApiIgnore
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping(value = "validate")
    @ApiOperation(value = "参数校验",notes = "参数校验")
    @CtrlAop
    public Result testValidate() throws Exception{
        throw new ParamException("测试validate");
    }

    @GetMapping(value = "empty")
    @ApiOperation(value = "参数为空",notes = "参数为空")
    @CtrlAop
    public Result testNull() throws Exception{
        throw new ParamException("测试null");
    }

    @GetMapping(value = "system")
    @ApiOperation(value = "系统异常",notes = "系统异常")
    @CtrlAop
    public Result testSystemException(){
        throw new SystemException("系统异常");
    }

    @GetMapping(value = "success")
    @ApiOperation(value = "正常请求",notes = "正常请求")
    @CtrlAop
    public Result<String> success(){
        logger.info("success ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return ResultFactory.success("Hello World!");
    }


    @GetMapping(value = "timeout")
    @ApiOperation(value = "超时请求",notes = "超时请求")
    @CtrlAop
    public Result<String> timeOut(DateQueryBase dateQueryBase) throws Exception{
        Thread.sleep(10000);
        return ResultFactory.success("Hello Time Out");
    }
}
