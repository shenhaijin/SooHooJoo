package com.example.soo.controller;

import com.example.common.page.SooPage;
import com.example.common.response.Result;
import com.example.common.response.ResultFactory;
import com.example.soo.bean.entity.SysOperaLog;
import com.example.soo.service.ISysOperaLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shenhaijin
 * @Date 2021/1/4 15:48
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@Api(tags = "系统操作日志")
@RequestMapping("sys/log")
public class SysOperaLogController {
    @Autowired
    ISysOperaLogService sysOperaLogService;
    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询",notes = "系统操作日志分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex",value = "当前页",required = false,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,defaultValue = "5"),
            @ApiImplicitParam(name = "userName",value = "用户名",required = false)
    })
    Result<SooPage<SysOperaLog>> pageSysOperaLog(
            @RequestParam(defaultValue = "1",required = false)Long pageIndex,
            @RequestParam(defaultValue = "5",required = false)Long pageSize,
            @RequestParam(required = false) String userName) throws Exception{
        SooPage<SysOperaLog> sysOperaLogPage = sysOperaLogService.pageSysOperaLog(pageIndex,pageSize,userName);
        return ResultFactory.success(sysOperaLogPage);
    }
}
