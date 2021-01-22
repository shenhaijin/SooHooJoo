package com.example.soo.controller;

import com.example.common.response.Result;
import com.example.common.response.ResultFactory;
import com.example.common.page.PageHelper;
import com.example.soo.aop.CtrlAop;
import com.example.soo.bean.entity.SysMenu;
import com.example.soo.bean.query.QueryMenuPage;
import com.example.soo.bean.query.SysMenuBase;
import com.example.soo.bean.query.SysMenuUpdate;
import com.example.soo.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 19:57
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("menu")
@Api(tags = "系统菜单管理")
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
    @CtrlAop
    public Result<Boolean> updateMenu(SysMenuUpdate sysMenuUpdate) throws Exception{
        boolean updateResult = sysMenuService.updateMenu(sysMenuUpdate);
        return ResultFactory.success(updateResult);
    }

    @DeleteMapping("delete")
    @ApiOperation(value = "删除菜单",notes = "删除系统菜单")
    @ApiImplicitParam(name = "menuId",value = "菜单Id",required = true)
    @CtrlAop
    public Result<Boolean> deleteMenu(String menuId) throws Exception{
        boolean deleteResult = sysMenuService.deleteMenu(menuId);
        return ResultFactory.success(deleteResult);
    }

    @GetMapping("page")
    @ApiOperation(value = "菜单列表",notes = "菜单分页列表")
    @CtrlAop
    public Result<PageHelper<SysMenu>> pageMenu(QueryMenuPage queryMenuPage) throws Exception{
        PageHelper<SysMenu> sysMenuSooPage = sysMenuService.pageMenu(queryMenuPage);
        return ResultFactory.success(sysMenuSooPage);
    }
}
