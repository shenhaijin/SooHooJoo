package com.example.soo.config;

import com.example.soo.handler.RateLimitHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author shenhaijin
 * @Date 2020/12/21 16:14
 * @Description 注册拦截器
 * @Version 1.0
 **/
@Configuration
public class SooAppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new RateLimitHandler()).addPathPatterns("/test/**");
    }
}
