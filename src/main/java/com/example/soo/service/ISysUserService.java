package com.example.soo.service;

import com.example.common.page.SooPage;
import com.example.soo.bean.entity.SysUser;
import com.example.soo.bean.query.SysUserBase;
import com.example.soo.bean.query.SysUserUpdate;

import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 18:26
 * @Description TODO
 * @Version 1.0
 **/
public interface ISysUserService {
    SysUser findOneUser(String userName, String passWord) throws Exception;
    SysUser sel(String id) throws Exception;
    boolean deleteUser(String userId) throws Exception;
    List<SysUser> getListUser() throws Exception;
    boolean saveUser(SysUserBase sysUserBase) throws Exception;
    Boolean updateUser(SysUserUpdate sysUserUpdate) throws Exception;
    SooPage<SysUser> pageUser(Long pageIndex, Long pageSize, String userName) throws Exception;
    List<SysUser> findUserByCondition(SysUser user) throws Exception;
}
