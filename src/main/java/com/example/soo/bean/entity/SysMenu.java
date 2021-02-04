package com.example.soo.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.soo.bean.dto.MenuDTO;

import java.io.Serializable;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 15:03
 * @Description TODO
 * @Version 1.0
 **/
@TableName("sys_menu")
public class SysMenu extends BaseEntity implements Serializable {
    @TableId(type = IdType.UUID)
    private String id;
    @TableField("menu_name")
    private String menuName;
    @TableField("menu_path")
    private String menuPath;
    @TableField("menu_type")
    private String menuType;
    @TableField("system")
    private String system;
    @TableField("parent_id")
    private String parentId;
    @TableField("level")
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

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

    @Override
    public String toString() {
        return "SysMenu{" +
                "id='" + id + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuPath='" + menuPath + '\'' +
                ", menuType='" + menuType + '\'' +
                ", system='" + system + '\'' +
                ", parentId='" + parentId + '\'' +
                ", level='" + level + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
