package com.example.soo.service;

import com.example.common.page.PageHelper;
import com.example.soo.bean.entity.SysMenu;
import com.example.soo.bean.query.QueryMenuPage;
import com.example.soo.bean.query.SysMenuBase;
import com.example.soo.bean.query.SysMenuUpdate;

import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 18:24
 * @Description TODO
 * @Version 1.0
 **/
public interface ISysMenuService {
    boolean addMenu(SysMenuBase sysMenuBase) throws Exception;
    boolean updateMenu(SysMenuUpdate sysMenuUpdate) throws Exception;
    boolean deleteMenu(String menuId) throws Exception;
    List<SysMenu> findUserAllMenuList(String userId) throws Exception;
    PageHelper<SysMenu> pageMenu(QueryMenuPage queryMenuPage) throws Exception;
}
