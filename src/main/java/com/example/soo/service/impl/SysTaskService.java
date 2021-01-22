package com.example.soo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.constant.RunStatus;
import com.example.common.page.PageHelper;
import com.example.soo.bean.entity.SysTaskConfig;
import com.example.soo.bean.query.TaskBase;
import com.example.soo.bean.query.TaskUpdate;
import com.example.soo.exception.ParamException;
import com.example.soo.exception.ResultException;
import com.example.soo.mapper.SysTaskConfigMapper;
import com.example.soo.service.ISysTaskService;
import com.example.soo.task.JobManager;
import com.example.soo.util.ConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 9:17
 * @Description 定时任务 任务的暂停、恢复都是通过调度的添加和删除实现
 * @Version 1.0
 **/
@Service
public class SysTaskService implements ISysTaskService {
    @Autowired
    JobManager jobManager;
    @Resource
    SysTaskConfigMapper sysTaskConfigMapper;
    @Override
    public List<SysTaskConfig> findTaskList() throws Exception{
        List<SysTaskConfig> sysTaskConfigList = sysTaskConfigMapper.selectList(null);
        return sysTaskConfigList;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTask(String taskId) throws Exception{
        if(StringUtils.isEmpty(taskId)){
            throw new ParamException("taskId为空");
        }
        SysTaskConfig sysTaskConfig = sysTaskConfigMapper.selectById(taskId);
        if(!ObjectUtils.isEmpty(sysTaskConfig)){
            UpdateWrapper<SysTaskConfig> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id",taskId);
            sysTaskConfigMapper.delete(updateWrapper);
            jobManager.deleteJob(sysTaskConfig);
        }
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveTask(TaskBase taskBase) throws Exception{
        if(taskBase == null){
            throw new ParamException("参数为空");
        }
        String className = taskBase.getTaskClass();
        if(StringUtils.isEmpty(className)){
            throw new ParamException("任务执行类为空");
        }
        String taskName = taskBase.getTaskName();
        if(StringUtils.isEmpty(taskName)){
            throw new ParamException("任务名为空");
        }
        String taskCron = taskBase.getTaskCron();
        if(StringUtils.isEmpty(taskCron)){
            throw new ParamException("cron为空");
        }
        QueryWrapper<SysTaskConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_class",className);
        queryWrapper.or();
        queryWrapper.eq("task_name",taskName);
        List<SysTaskConfig> sysTaskConfigList = sysTaskConfigMapper.selectList(queryWrapper);
        if(!CollectionUtils.isEmpty(sysTaskConfigList)){
            throw new ResultException("任务名或任务执行类已存在");
        }
        SysTaskConfig sysTaskConfig = new SysTaskConfig();
        BeanUtils.copyProperties(taskBase,sysTaskConfig);
        sysTaskConfig.setCreateTime(new Date());
        sysTaskConfig.setUpdateTime(sysTaskConfig.getCreateTime());
        int number = sysTaskConfigMapper.insert(sysTaskConfig);
        if(number < 1){
            throw new ResultException("保存失败");
        }
        jobManager.addJob(sysTaskConfig);
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTask(TaskUpdate taskUpdate) throws Exception{
        if(taskUpdate == null){
            throw new ParamException("参数为空");
        }
        SysTaskConfig oldSysTaskConfig = commonTaskJob(taskUpdate.getTaskId());
        SysTaskConfig curSysTaskConfig = new SysTaskConfig();
        BeanUtils.copyProperties(oldSysTaskConfig,curSysTaskConfig);
        if(!StringUtils.isEmpty(taskUpdate.getTaskName())){
            curSysTaskConfig.setTaskName(taskUpdate.getTaskName());
        }
        if(!StringUtils.isEmpty(taskUpdate.getTaskClass())){
            curSysTaskConfig.setTaskClass(taskUpdate.getTaskClass());
        }
        if(!StringUtils.isEmpty(taskUpdate.getTaskCron())){
            curSysTaskConfig.setTaskCron(taskUpdate.getTaskCron());
        }
        if(!StringUtils.isEmpty(taskUpdate.getTaskGroup())){
            curSysTaskConfig.setTaskGroup(taskUpdate.getTaskGroup());
        }
        if(!StringUtils.isEmpty(taskUpdate.getTaskExplain())){
            curSysTaskConfig.setTaskExplain(taskUpdate.getTaskExplain());
        }
        if(!StringUtils.isEmpty(taskUpdate.getTaskStatus())){
            curSysTaskConfig.setTaskStatus(taskUpdate.getTaskStatus());
        }
        curSysTaskConfig.setUpdateTime(new Date());
        int number = sysTaskConfigMapper.updateById(curSysTaskConfig);
        if(number < 1){
            throw new ResultException("修改失败");
        }
        jobManager.updateJob(oldSysTaskConfig,curSysTaskConfig);
        return true;
    }
    @Override
    public PageHelper<SysTaskConfig> findTaskPage(Long pageIndex, Long pageSize, String taskName) throws Exception{
        QueryWrapper<SysTaskConfig> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(taskName)){
            wrapper.like("task_name",taskName);
        }
        wrapper.orderByDesc("create_time");
        IPage<SysTaskConfig> sysTaskPage = new Page(pageIndex,pageSize);
        sysTaskPage = sysTaskConfigMapper.selectPage(sysTaskPage,wrapper);
        PageHelper<SysTaskConfig> sooPage = ConvertUtil.mybatisPageConvertPageHelper(sysTaskPage);
        return sooPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean pauseTaskJob(String taskId) throws Exception{
        SysTaskConfig sysTaskConfig = commonTaskJob(taskId);
        if(RunStatus.PAUSE.getCode().equals(sysTaskConfig.getTaskStatus())){
            throw new ResultException("当前任务已暂停！");
        }
        sysTaskConfig.setUpdateTime(new Date());
        sysTaskConfig.setTaskStatus(RunStatus.PAUSE.getCode());
        int number = sysTaskConfigMapper.updateById(sysTaskConfig);
        if (number < 1){
            throw new ResultException("暂停任务失败！");
        }
        jobManager.deleteJob(sysTaskConfig);
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean runTaskJob(String taskId) throws Exception{
        SysTaskConfig sysTaskConfig = commonTaskJob(taskId);
        if(RunStatus.RUN.getCode().equals(sysTaskConfig.getTaskStatus())){
            throw new ResultException("当前任务已运行！");
        }
        sysTaskConfig.setUpdateTime(new Date());
        sysTaskConfig.setTaskStatus(RunStatus.RUN.getCode());
        int number = sysTaskConfigMapper.updateById(sysTaskConfig);
        if (number < 1){
            throw new ResultException("启动任务失败！");
        }
        jobManager.addJob(sysTaskConfig);
        return true;
    }

    private SysTaskConfig commonTaskJob(String taskId) throws Exception{
        if(StringUtils.isEmpty(taskId)){
            throw new ParamException("taskId为空");
        }
        SysTaskConfig sysTaskConfig = sysTaskConfigMapper.selectById(taskId);
        if(ObjectUtils.isEmpty(sysTaskConfig)){
            throw new ResultException("任务不存在！");
        }
        return sysTaskConfig;
    }
}
