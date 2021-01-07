package com.example.soo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shenhaijin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CtrlAop {
    /**
     * 接口是否需要校验权限
     * @return
     */
    boolean isNeedCheckAuth() default true;
}
