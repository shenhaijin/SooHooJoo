package com.example.soo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.page.PageHelper;
import com.example.soo.bean.entity.SysRole;
import com.example.soo.bean.entity.SysRoleMenu;
import com.example.soo.bean.query.QueryRolePage;
import com.example.soo.bean.query.SysRoleMenuBase;
import com.example.soo.bean.query.SysRoleMenuUpdate;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 18:25
 * @Description TODO
 * @Version 1.0
 **/
public interface ISysRoleService extends IService<SysRoleMenu> {
    boolean addRole(SysRoleMenuBase sysRoleMenuBase) throws Exception;
    boolean updateRole(SysRoleMenuUpdate sysRoleMenuUpdate) throws Exception;
    PageHelper<SysRole> pageRole(QueryRolePage queryRolePage) throws Exception;
    boolean deleteRole(String roleId) throws Exception;
}
