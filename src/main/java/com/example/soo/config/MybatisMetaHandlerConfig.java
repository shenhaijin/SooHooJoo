package com.example.soo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.soo.util.UserUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author shenhaijin
 * @Date 2021/2/3 16:37
 * @Description 在插入或修改数据库之前执行的操作
 * @Version 1.0
 **/
@Component
public class MybatisMetaHandlerConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Date curDate = new Date();
        try{
            setFieldValByName("userId", UserUtil.getUserId(),metaObject);
            setFieldValByName("createTime",curDate,metaObject);
            setFieldValByName("updateTime",curDate,metaObject);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        Date curDate = new Date();
        setFieldValByName("updateTime",curDate,metaObject);
    }
}
