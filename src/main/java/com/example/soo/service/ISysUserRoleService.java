package com.example.soo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.soo.bean.entity.SysUserRole;
import com.example.soo.bean.query.UserRoleUpdate;

public interface ISysUserRoleService extends IService<SysUserRole> {
    boolean addUserRole(UserRoleUpdate userRoleUpdate) throws Exception;
    boolean updateUserRole(UserRoleUpdate userRoleUpdate) throws Exception;
    boolean deleteUserRole(String userRoleId) throws Exception;
}
