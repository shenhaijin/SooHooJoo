package com.example.soo.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 16:12
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel("编辑用户对象")
public class SysUserUpdate extends SysUserBase {
    @ApiModelProperty("用户Id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SysUserUpdate{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", realName='" + realName + '\'' +
                ", superAdmin='" + superAdmin + '\'' +
                '}';
    }
}
