package com.example.soo.bean.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

/**
 * @Author shenhaijin
 * @Date 2021/2/1 10:44
 * @Description TODO
 * @Version 1.0
 **/
public class BaseEntity {
    @TableField(value = "user_id",fill = FieldFill.INSERT)
    protected String userId;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    protected Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
