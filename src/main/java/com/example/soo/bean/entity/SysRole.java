package com.example.soo.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 15:08
 * @Description TODO
 * @Version 1.0
 **/
@TableName("sys_role")
@ApiModel("角色对象")
public class SysRole implements Serializable {
    @TableId(type = IdType.UUID)
    @ApiModelProperty("主键Id")
    private String id;
    @TableField("role_name")
    @ApiModelProperty("角色名")
    private String roleName;
    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty("修改时间")
    private Date updateTime;

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

    @Override
    public String toString() {
        return "SysRole{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
