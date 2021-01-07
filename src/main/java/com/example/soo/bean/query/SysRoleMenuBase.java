package com.example.soo.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 17:44
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel("添加角色对象")
public class SysRoleMenuBase {
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("菜单列表")
    private List<String> menuList;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<String> menuList) {
        this.menuList = menuList;
    }
}
