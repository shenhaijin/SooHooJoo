package com.example.soo.bean.query;

import com.example.soo.bean.entity.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2021/1/9 12:19
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel
public class SysMenuBase implements ConvertBase<SysMenu>{
    @ApiModelProperty("菜单名称")
    protected String menuName;
    @ApiModelProperty("菜单路径")
    protected String menuPath;
    @ApiModelProperty("菜单类型")
    protected String menuType;

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

    @Override
    public SysMenu covert() {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuName(this.menuName);
        sysMenu.setMenuPath(this.menuPath);
        sysMenu.setMenuType(this.menuType);
        return sysMenu;
    }
}
