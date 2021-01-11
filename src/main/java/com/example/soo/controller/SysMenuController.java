package com.example.soo.controller;

import com.example.common.response.Result;
import com.example.common.response.ResultFactory;
import com.example.soo.aop.CtrlAop;
import com.example.soo.bean.query.SysMenuBase;
import com.example.soo.bean.query.SysMenuUpdate;
import com.example.soo.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 19:57
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("menu")
@Api("系统菜单管理")
public class SysMenuController {
    @Autowired
    ISysMenuService sysMenuService;
    @PostMapping("save")
    @ApiOperation(value = "添加菜单",notes = "添加系统菜单")
    @CtrlAop
    public Result<Boolean> addMenu(SysMenuBase sysMenuBase) throws Exception{
        boolean addResult = sysMenuService.addMenu(sysMenuBase);
        return ResultFactory.success(addResult);
    }
    @PutMapping("update")
    @ApiOperation(value = "编辑菜单",notes = "编辑系统菜单")
    public Result<Boolean> updateMenu(SysMenuUpdate sysMenuUpdate) throws Exception{
        boolean updateResult = sysMenuService.updateMenu(sysMenuUpdate);
        return ResultFactory.success(updateResult);
    }
}
