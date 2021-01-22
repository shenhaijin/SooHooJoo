package com.example.soo.controller;

import com.example.common.response.Result;
import com.example.common.response.ResultFactory;
import com.example.common.page.PageHelper;
import com.example.soo.aop.CtrlAop;
import com.example.soo.bean.entity.SysUser;
import com.example.soo.bean.query.SysUserBase;
import com.example.soo.bean.query.SysUserUpdate;
import com.example.soo.service.ISysUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2020/12/22 12:03
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("user")
@Api(tags = "系统用户管理")
public class SysUserController {
    @Autowired
    ISysUserService sysUserService;

    @DeleteMapping("delete")
    @ApiOperation(value="删除用户",notes = "根据用户id删除用户，返回删除的用户信息")
    @ApiImplicitParam(name = "userId",value = "用户Id",required = true)
    @CtrlAop
    public Result<Boolean> deleteUser( @RequestParam("userId") String userId) throws Exception {
        boolean deleteResult = sysUserService.deleteUser(userId);
        return ResultFactory.success(deleteResult);
    }
    @GetMapping("detail")
    @ApiOperation(value="用户详情",notes = "根据用户Id查询用户详情")
    @ApiImplicitParam(name = "userId",value = "用户Id",required = true)
    @CtrlAop
    public Result<SysUser> detail(@RequestParam("userId") String userId)throws Exception{
        SysUser queryUser = sysUserService.sel(userId);
        return ResultFactory.success(queryUser);
    }

    @GetMapping("list")
    @ApiOperation(value="用户列表",notes = "用户列表")
    @CtrlAop
    public Result<List<SysUser>> list() throws Exception{
        List<SysUser> queryListUser = sysUserService.getListUser();
        return ResultFactory.success(queryListUser);
    }

    @PostMapping(value = "save")
    @ApiOperation(value = "创建用户",notes = "创建新用户")
    @CtrlAop
    public Result<Boolean> save(SysUserBase sysUserBase) throws Exception{
        boolean saveResult = sysUserService.saveUser(sysUserBase);
        return ResultFactory.success(saveResult);
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑用户",notes = "编辑用户")
    @CtrlAop
    public Result<Boolean> save(SysUserUpdate sysUserUpdate) throws Exception{
        boolean updateResult = sysUserService.updateUser(sysUserUpdate);
        return ResultFactory.success(updateResult);
    }
    @GetMapping(value = "page")
    @ApiOperation(value = "分页列表",notes = "用户分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex",value = "当前页",required = false,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,defaultValue = "5"),
            @ApiImplicitParam(name = "userName",value = "用户名",required = false)
    })
    @CtrlAop
    public Result<PageHelper<SysUser>> pageUser(@RequestParam(defaultValue = "1",required = false)Long pageIndex,
                                          @RequestParam(defaultValue = "5",required = false)Long pageSize,
                                          @RequestParam(required = false) String userName) throws Exception{
        PageHelper<SysUser> userPage = sysUserService.pageUser(pageIndex,pageSize,userName);
        return ResultFactory.success(userPage);
    }
}
