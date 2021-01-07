package com.example.soo.bean.query;

import com.example.common.base.PageQueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 19:12
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel
public class QueryRolePage extends PageQueryBase {
    @ApiModelProperty("角色名")
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
