package com.example.soo.controller;

import com.example.common.response.Result;
import com.example.common.response.ResultFactory;
import com.example.soo.aop.CtrlAop;
import com.example.soo.bean.entity.SysUser;
import com.example.soo.exception.ParamException;
import com.example.soo.service.ISysUserService;
import com.example.soo.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author shenhaijin
 * @Date 2021/1/4 11:22
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@Api(tags = "系统登录接口")
public class LoginCtroller {
    @Autowired
    ISysUserService sysUserService;
    @PostMapping(value = "/api/auth/login")
    @ApiOperation(value = "登陆接口",notes = "登陆接口,返回接口权限token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "每页大小",required = true)
    })
    @CtrlAop(isNeedCheckAuth = false)
    public Result<String> login(@RequestParam("username") String userName,@RequestParam("password") String passWord)
            throws Exception{
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)){
            throw new ParamException("用户名或密码为空");
        }
        SysUser user = sysUserService.findOneUser(userName,passWord);
        if(ObjectUtils.isEmpty(user)){
            throw new ParamException("用户或密码错误!");
        }
        String token = JWTUtil.createJWT(userName,passWord);
        return ResultFactory.success(token);
    }

}
