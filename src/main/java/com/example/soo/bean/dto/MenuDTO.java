package com.example.soo.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author shenhaijin
 * @Date 2021/2/3 15:42
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel
public class MenuDTO  {
    @ApiModelProperty("菜单Id")
    private String id;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单路径")
    private String menuPath;
    @ApiModelProperty("菜单类型")
    private String menuType;
    @ApiModelProperty("所属系统")
    private String system;
    @ApiModelProperty("父菜单Id")
    private String parentId;
    @ApiModelProperty("菜单层级")
    private String level;
    @ApiModelProperty("创建者Id")
    private String userId;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("修改时间")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

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
