package com.example.soo.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author shenhaijin
 * @Date 2020/12/21 15:13
 * @Description 用户对象
 * @Version 1.0
 **/
@ApiModel("用户对象")
@TableName("sys_user")
public class SysUser {
    @ApiModelProperty("用户Id")
    @TableId(type = IdType.UUID)
    private String id;
    @ApiModelProperty("用户名称")
    @TableField("user_name")
    private String userName;
    @ApiModelProperty("登陆密码")
    @TableField("pass_word")
    private String passWord;
    @ApiModelProperty("真实姓名")
    @TableField("real_name")
    private String realName;
    @ApiModelProperty("超级管理员标识 1：超级管理员")
    @TableField("super_admin")
    private String superAdmin;
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
    @ApiModelProperty("修改时间")
    @TableField("update_time")
    private Date updateTime;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(String superAdmin) {
        this.superAdmin = superAdmin;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", realName='" + realName + '\'' +
                ", superAdmin='" + superAdmin + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
