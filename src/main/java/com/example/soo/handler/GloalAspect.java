package com.example.soo.handler;

import com.example.common.code.error.ExceptionCode;
import com.example.common.constant.OperatStatus;
import com.example.common.response.Result;
import com.example.common.response.ResultFactory;
import com.example.soo.aop.CtrlAop;
import com.example.soo.bean.entity.SysMenu;
import com.example.soo.bean.entity.SysOperaLog;
import com.example.soo.bean.entity.SysUser;
import com.example.soo.exception.AuthException;
import com.example.soo.exception.SooException;
import com.example.soo.service.ISysMenuService;
import com.example.soo.service.ISysUserService;
import com.example.soo.service.impl.SysMenuService;
import com.example.soo.service.impl.SysOperaLogService;
import com.example.soo.util.JWTUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/4 12:12
 * @Description TODO
 * @Version 1.0
 **/
@Aspect
@Component
public class GloalAspect {
    private static final String SYS_OPERA_PATH = "/sys/log/page";       //  日志列表接口
    private static final String SUPER_ADMIN_FLAG = "1";                 //  超级管理员标识
    private static final String ACCESS_TOKEN = "token";
    @Autowired
    ISysUserService sysUserService;
    @Autowired
    ISysMenuService sysMenuService;
    @Autowired
    SysOperaLogService sysOperaLogService;
    @Pointcut("execution(* com.example.soo.controller..*(..))")
    public void pointCut() {}
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        SysOperaLog sysOperaLog = new SysOperaLog();
        sysOperaLog.setOperationTime(new Date(System.currentTimeMillis()));
        sysOperaLog.setOperatStatus(OperatStatus.FAIL.getCode());
        Signature signature = proceedingJoinPoint.getSignature();
        Method method = ((MethodSignature) signature).getMethod();
        sysOperaLog.setMethodName(method.getName());
        sysOperaLog.setClassName(signature.getDeclaringType().getName());
        Object responseObj = null;
        try {
            checkUserMenuAuth(sysOperaLog,method);
            responseObj = proceedingJoinPoint.proceed();
        }catch (SooException sooException){
            sooException.printStackTrace();
            responseObj = ResultFactory.fail(sooException.getExceptionCode().getExceCode(),sooException.getExceptionMessage());
        }catch (Exception exception){
            exception.printStackTrace();
            responseObj = ResultFactory.fail(ExceptionCode.SYSTEM.getExceCode(),ExceptionCode.SYSTEM.getCodeDesc());
        }catch (Throwable throwable){
            throwable.printStackTrace();
            responseObj = ResultFactory.fail(ExceptionCode.SYSTEM.getExceCode(),ExceptionCode.SYSTEM.getCodeDesc());
        }finally {
            if(responseObj instanceof Result){
                Result backResult = (Result) responseObj;
                String code = backResult.getCode();
                if(ExceptionCode.SUCCESS.getExceCode().equalsIgnoreCase(code)){
                    sysOperaLog.setOperatStatus(OperatStatus.SUCCESS.getCode());
                }
            }
            writeSysOperaLog(sysOperaLog);
        }
        return responseObj;
    }

    private void checkUserMenuAuth(SysOperaLog sysOperaLog,Method method) throws Exception{
        if(method.isAnnotationPresent(CtrlAop.class)){
            CtrlAop ctrlAop = method.getAnnotation(CtrlAop.class);
            boolean isNeedCheckAuth = ctrlAop.isNeedCheckAuth();
            if(isNeedCheckAuth){
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
                String authToken = httpServletRequest.getHeader(ACCESS_TOKEN);
                if(StringUtils.isEmpty(authToken)){
                    throw new AuthException("token为空");
                }
                String userName = JWTUtil.decodeToken(authToken);
                SysUser condition = new SysUser();
                condition.setUserName(userName);
                List<SysUser> userList = sysUserService.findUserByCondition(condition);
                if(CollectionUtils.isEmpty(userList)){
                    throw new AuthException("token无效");
                }
                sysOperaLog.setOperator(userName);
                String superAdmin = userList.get(0).getSuperAdmin();
                //非超级管理员需要权限
                if(!SUPER_ADMIN_FLAG.equalsIgnoreCase(superAdmin)){
                    String userId = userList.get(0).getId();
                    List<SysMenu> sysMenuList = sysMenuService.findUserAllMenuList(userId);
                    if(CollectionUtils.isEmpty(sysMenuList)){
                        throw new AuthException("用户无任何权限！");
                    }
                    boolean validateResult = false;
                    String reqPath = httpServletRequest.getServletPath();
                    for(SysMenu sysMenu : sysMenuList){
                        String menuPath = sysMenu.getMenuPath();
                        menuPath = menuPath.trim();
                        if(reqPath.startsWith(menuPath)){
                            validateResult = true;
                            break;
                        }
                    }
                    if(!validateResult){
                        throw new AuthException("用户【"+userName+"】 无该接口 >>> " + reqPath + " <<<权限");
                    }
                }
            }
        }
    }

    private void writeSysOperaLog(SysOperaLog sysOperaLog)  {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
            String reqPath = httpServletRequest.getServletPath();
            if(!SYS_OPERA_PATH.equals(reqPath)){
                String visitIp = getRealIp(httpServletRequest);
                sysOperaLog.setVisitIp(visitIp);
                sysOperaLogService.saveSysOperaLog(sysOperaLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getRealIp(HttpServletRequest httpServletRequest) throws Exception{
        String ipAddress = httpServletRequest.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpServletRequest.getHeader("WL-Proxy-Client-IP");
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
