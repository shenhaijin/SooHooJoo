package com.example.soo.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.example.common.constant.Constant.ACCESS_TOKEN_KEY;

/**
 * @Author shenhaijin
 * @Date 2021/2/3 14:25
 * @Description TODO
 * @Version 1.0
 **/
public class UserUtil {
    /**
     * 获取当前登陆用户Id
     * @return
     */
    public static String getUserId() throws Exception{
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        String authToken = httpServletRequest.getHeader(ACCESS_TOKEN_KEY);
        String userId = JWTUtil.getUserId(authToken);
        return userId;
    }
}
