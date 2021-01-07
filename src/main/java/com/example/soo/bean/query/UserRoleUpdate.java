package com.example.soo.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 20:02
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel
public class UserRoleUpdate {
    @ApiModelProperty("用户Id")
    private String userId;
    @ApiModelProperty("角色id列表")
    private List<String> roleIdList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
