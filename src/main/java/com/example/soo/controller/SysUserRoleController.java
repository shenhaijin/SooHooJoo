package com.example.soo.controller;

import com.example.common.response.Result;
import com.example.common.response.ResultFactory;
import com.example.soo.aop.CtrlAop;
import com.example.soo.bean.query.UserRoleUpdate;
import com.example.soo.service.ISysUserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 19:57
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("user/role")
public class SysUserRoleController {
    @Autowired
    ISysUserRoleService sysUserRoleService;
    @PostMapping("add")
    @ApiOperation(value = "添加用户角色",notes = "添加用户角色信息")
    @CtrlAop
    public Result<Boolean> addUserRole(UserRoleUpdate userRoleUpdate) throws Exception{
        boolean addResult = sysUserRoleService.addUserRole(userRoleUpdate);
        return ResultFactory.success(addResult);
    }
    @PostMapping("update")
    @ApiOperation(value = "修改用户角色",notes = "修改用户角色信息")
    @CtrlAop
    public Result<Boolean> updateUserRole(UserRoleUpdate userRoleUpdate) throws Exception{
        boolean updateResult = sysUserRoleService.updateUserRole(userRoleUpdate);
        return ResultFactory.success(updateResult);
    }
    @DeleteMapping("delete")
    @ApiOperation(value = "删除用户角色",notes = "删除用户角色信息")
    @CtrlAop
    public Result<Boolean> deleteUserRole(String userRoleId) throws Exception{
        boolean deleteResult = sysUserRoleService.deleteUserRole(userRoleId);
        return ResultFactory.success(deleteResult);
    }
}