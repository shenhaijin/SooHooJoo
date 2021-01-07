package com.example.soo.handler;

import com.example.soo.exception.SystemException;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author shenhaijin
 * @Date 2020/12/21 16:08
 * @Description 令牌桶限流
 * @Version 1.0
 **/
public class RateLimitHandler implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RateLimitHandler.class);
    private static final RateLimiter rateLimiter = RateLimiter.create(0.1);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        /**
         * rateLimiter.tryAcquire
         * 立即返回结果、获取不到令牌
         * 该请求直接丢弃、做法比较暴力
         */
        if(!rateLimiter.tryAcquire()){
            logger.info("丢弃 --> " + requestUri);
            throw new SystemException(requestUri + " --> 限流");
        }
        logger.info("处理 --> " + requestUri);
        return true;
    }
}
