package com.example.soo.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 15:08
 * @Description TODO
 * @Version 1.0
 **/
@TableName("sys_role")
@ApiModel("角色对象")
public class SysRole extends BaseEntity implements Serializable {
    @TableId(type = IdType.UUID)
    @ApiModelProperty("主键Id")
    private String id;
    @TableField("role_name")
    @ApiModelProperty("角色名")
    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
