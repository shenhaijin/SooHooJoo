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
import com.example.soo.service.impl.SysOperaLogService;
import com.example.soo.util.JWTUtil;
import com.example.soo.util.ReqUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import static com.example.common.constant.Constant.ACCESS_TOKEN_KEY;
import static com.example.common.constant.Constant.SUPER_ADMIN_FLAG;

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
        sysOperaLog.setStatus(OperatStatus.FAIL.getCode());
        Signature signature = proceedingJoinPoint.getSignature();
        Method method = ((MethodSignature) signature).getMethod();
        sysOperaLog.setMethodName(method.getName());
        sysOperaLog.setClassName(signature.getDeclaringType().getName());
        long startTime = System.currentTimeMillis();
        sysOperaLog.setCreateTime(new Date(startTime));
        Object responseObj = null;
        HttpServletRequest httpServletRequest = ReqUtil.getRequest();
        try {
            checkUserMenuAuth(sysOperaLog,method,httpServletRequest);
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
                    sysOperaLog.setStatus(OperatStatus.SUCCESS.getCode());
                }
            }
            long endTime = System.currentTimeMillis();
            sysOperaLog.setTakeupTime(endTime - startTime);
            writeSysOperaLog(sysOperaLog,httpServletRequest);
        }
        return responseObj;
    }

    private void checkUserMenuAuth(SysOperaLog sysOperaLog,Method method,HttpServletRequest httpServletRequest) throws Exception{
        if(method.isAnnotationPresent(CtrlAop.class)){
            CtrlAop ctrlAop = method.getAnnotation(CtrlAop.class);
            boolean isNeedCheckAuth = ctrlAop.isNeedCheckAuth();
            if(isNeedCheckAuth){
                String authToken = ReqUtil.getHeader(httpServletRequest,ACCESS_TOKEN_KEY);
                if(StringUtils.isEmpty(authToken)){
                    throw new AuthException("token为空");
                }
                JWTUtil.verifyToken(authToken);
                String userId = JWTUtil.getUserId(authToken);
                SysUser sysUser = sysUserService.sel(userId);
                if(ObjectUtils.isEmpty(sysUser)){
                    throw new AuthException("token无效");
                }
                sysOperaLog.setUserId(userId);
                sysOperaLog.setOperator(sysUser.getUserName());
                String superAdmin = sysUser.getSuperAdmin();
                //非超级管理员需要权限
                if(!SUPER_ADMIN_FLAG.equalsIgnoreCase(superAdmin)){
                    List<SysMenu> sysMenuList = sysMenuService.findUserAllMenuList(sysUser.getId());
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
                        throw new AuthException("用户【"+sysUser.getUserName()+"】 无该接口 >>> " + reqPath + " <<<权限");
                    }
                }
            }
        }
    }

    private void writeSysOperaLog(SysOperaLog sysOperaLog,HttpServletRequest httpServletRequest)  {
        try {
            String reqPath = ReqUtil.getReqPath(httpServletRequest);
            if(!SYS_OPERA_PATH.equals(reqPath)){
                String visitIp = ReqUtil.getRealIp(httpServletRequest);
                String referer = ReqUtil.getHeader(httpServletRequest,"referer");
                sysOperaLog.setVisitIp(visitIp);
                sysOperaLog.setReferer(referer);
                sysOperaLogService.saveSysOperaLog(sysOperaLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
