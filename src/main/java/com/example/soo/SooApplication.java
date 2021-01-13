package com.example.soo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableSwagger2Doc
@SpringBootApplication
@EnableCaching
public class SooApplication {

    public static void main(String[] args) {
        SpringApplication.run(SooApplication.class, args);
    }

}
