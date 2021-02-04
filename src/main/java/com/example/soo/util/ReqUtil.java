package com.example.soo.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @Author shenhaijin
 * @Date 2021/2/3 16:48
 * @Description TODO
 * @Version 1.0
 **/
public class ReqUtil {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        return httpServletRequest;
    }

    /**
     * 避免多次查询当前请求对象
     * @param httpServletRequest
     * @return
     */
    public static String getReqPath(HttpServletRequest httpServletRequest){
        if(httpServletRequest == null){
            httpServletRequest = getRequest();
        }
        String reqPath = httpServletRequest.getServletPath();
        return reqPath;
    }

    /**
     * 获取请求头
     * @param httpServletRequest
     */
    public static String getHeader(HttpServletRequest httpServletRequest,String headerKey){
        if(httpServletRequest == null){
            httpServletRequest = getRequest();
        }
        String value = httpServletRequest.getHeader(headerKey);
        return value;
    }

    /**
     * 获取请求IP
     */
    public static String getRealIp(HttpServletRequest httpServletRequest) throws Exception{
        String ipAddress = ReqUtil.getHeader(httpServletRequest,"x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = ReqUtil.getHeader(httpServletRequest,"Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = ReqUtil.getHeader(httpServletRequest,"WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpServletRequest.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                ipAddress = InetAddress.getLocalHost().getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        // "***.***.***.***".length()
        if (ipAddress != null && ipAddress.length() > 15) {
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
