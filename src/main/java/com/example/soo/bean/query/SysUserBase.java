package com.example.soo.bean.query;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 16:11
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel("新增用户对象")
public class SysUserBase {
    @ApiModelProperty("用户名称")
    @TableField("user_name")
    protected String userName;
    @ApiModelProperty("登陆密码")
    @TableField("pass_word")
    protected String passWord;
    @ApiModelProperty("真实姓名")
    @TableField("real_name")
    protected String realName;
    @ApiModelProperty("超级管理员标识 1：超级管理员")
    @TableField("super_admin")
    protected String superAdmin;

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

    public String getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(String superAdmin) {
        this.superAdmin = superAdmin;
    }

    @Override
    public String toString() {
        return "SysUserBase{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", realName='" + realName + '\'' +
                ", superAdmin='" + superAdmin + '\'' +
                '}';
    }
}
