package com.example.soo.bean.query;

import com.example.soo.bean.entity.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

/**
 * @Author shenhaijin
 * @Date 2021/1/9 12:32
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel
public class SysMenuUpdate extends SysMenuBase {
    @ApiModelProperty("菜单Id")
    private String menuId;

    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    @Override
    public SysMenu convert() {
        SysMenu sysMenu = super.convert();
        sysMenu.setId(this.menuId);
        return sysMenu;
    }

    public SysMenu convert(SysMenu sysMenu){
        if(!StringUtils.isEmpty(this.menuName)){
            sysMenu.setMenuName(this.menuName);
        }
        if(!StringUtils.isEmpty(this.menuPath)){
            sysMenu.setMenuPath(this.menuPath);
        }
        if(!StringUtils.isEmpty(this.menuType)){
            sysMenu.setMenuType(this.menuType);
        }
        return sysMenu;
    }
}
