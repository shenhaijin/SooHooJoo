package com.example.soo.controller;

import com.example.common.page.SooPage;
import com.example.common.response.Result;
import com.example.common.response.ResultFactory;
import com.example.soo.aop.CtrlAop;
import com.example.soo.bean.entity.SysTaskConfig;
import com.example.soo.bean.query.TaskBase;
import com.example.soo.bean.query.TaskUpdate;
import com.example.soo.service.ISysTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 9:30
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("task")
@Api(tags = "定时任务管理")
public class SysTaskController {
    @Autowired
    ISysTaskService sysTaskService;
    @GetMapping(value = "page")
    @ApiOperation(value = "分页查询",notes = "定时任务分页列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex",value = "当前页",required = false,defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,defaultValue = "5"),
            @ApiImplicitParam(name = "taskName",value = "任务名称",required = false)
    })
    @CtrlAop
    public Result<SooPage<SysTaskConfig>> page(
            @RequestParam(defaultValue = "1",required = false)Long pageIndex,
            @RequestParam(defaultValue = "5",required = false)Long pageSize,
            @RequestParam(required = false) String taskName) throws Exception{
        SooPage<SysTaskConfig> sysTaskConfigSooPage = sysTaskService.findTaskPage(pageIndex,pageSize,taskName);
        return ResultFactory.success(sysTaskConfigSooPage);
    }
    @PutMapping("update")
    @ApiOperation(value = "编辑任务",notes = "编辑定时任务")
    @CtrlAop
    public Result<Boolean> updateTask(TaskUpdate taskUpdate) throws Exception{
        boolean result = sysTaskService.updateTask(taskUpdate);
        return ResultFactory.success(result);
    }
    @PostMapping("save")
    @ApiOperation(value = "新增任务",notes = "新增定时任务")
    @CtrlAop
    public Result<Boolean> saveTask(TaskBase taskBase) throws Exception{
        boolean result = sysTaskService.saveTask(taskBase);
        return ResultFactory.success(result);
    }
    @DeleteMapping("delete")
    @ApiOperation(value = "删除任务",notes = "删除定时任务")
    @ApiImplicitParam(name = "taskId",value = "任务Id",required = true)
    @CtrlAop
    public Result<Boolean> deleteTask(@RequestParam("taskId") String taskId) throws Exception{
        boolean result = sysTaskService.deleteTask(taskId);
        return ResultFactory.success(result);
    }
    @PostMapping("pause")
    @ApiOperation(value = "暂停任务",notes = "暂停定时任务")
    @ApiImplicitParam(name = "taskId",value = "任务Id",required = true)
    @CtrlAop
    public Result<Boolean> pauseTask(@RequestParam("taskId") String taskId) throws Exception{
        boolean result = sysTaskService.pauseTaskJob(taskId);
        return ResultFactory.success(result);
    }
    @PostMapping("run")
    @ApiOperation(value = "启动任务",notes = "启动定时任务")
    @ApiImplicitParam(name = "taskId",value = "任务Id",required = true)
    @CtrlAop
    public Result<Boolean> runTask(@RequestParam("taskId") String taskId) throws Exception{
        boolean result = sysTaskService.runTaskJob(taskId);
        return ResultFactory.success(result);
    }
}
