package com.example.soo.controller;

import com.example.common.page.SooPage;
import com.example.common.response.Result;
import com.example.common.response.ResultFactory;
import com.example.soo.aop.CtrlAop;
import com.example.soo.bean.entity.SysRole;
import com.example.soo.bean.query.QueryRolePage;
import com.example.soo.bean.query.SysRoleMenuBase;
import com.example.soo.bean.query.SysRoleMenuUpdate;
import com.example.soo.service.impl.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 17:46
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("role")
@Api(tags = "系统角色管理")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;

    @PostMapping("save")
    @ApiOperation(value = "添加角色",notes = "添加系统菜单角色")
    @CtrlAop
    public Result<Boolean> addRole(SysRoleMenuBase sysRoleMenuBase) throws Exception{
        boolean addResult = sysRoleService.addRole(sysRoleMenuBase);
        return ResultFactory.success(addResult);
    }
    @PutMapping("update")
    @ApiOperation(value = "修改角色",notes = "修改系统菜单角色")
    @CtrlAop
    public Result<Boolean> updateRole(SysRoleMenuUpdate sysRoleMenuUpdate) throws Exception{
        boolean updateResult = sysRoleService.updateRole(sysRoleMenuUpdate);
        return ResultFactory.success(updateResult);
    }
    @GetMapping("page")
    @ApiOperation(value = "分页列表",notes = "菜单角色分页列表查询")
    @CtrlAop
    public Result<SooPage<SysRole>> pageRole(QueryRolePage queryRolePage) throws Exception{
        SooPage<SysRole> sysRoleSooPage = sysRoleService.pageRole(queryRolePage);
        return ResultFactory.success(sysRoleSooPage);
    }
    @DeleteMapping("delete")
    @ApiOperation(value = "删除角色",notes = "删除角色菜单")
    @ApiImplicitParam(name = "roleId",value = "角色Id",required = true)
    @CtrlAop
    public Result<Boolean> deleteRole(@RequestParam("roleId") String roleId) throws Exception{
        boolean deleteResult = sysRoleService.deleteRole(roleId);
        return ResultFactory.success(deleteResult);
    }
}
