package com.example.soo.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 18:48
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel("编辑角色对象")
public class SysRoleMenuUpdate extends SysRoleMenuBase {
    @ApiModelProperty("角色Id")
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
