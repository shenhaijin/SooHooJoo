package com.example.soo.service;

import com.example.common.page.SooPage;
import com.example.soo.bean.entity.SysTaskConfig;
import com.example.soo.bean.query.TaskBase;
import com.example.soo.bean.query.TaskUpdate;

import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 18:25
 * @Description TODO
 * @Version 1.0
 **/
public interface ISysTaskService {
    List<SysTaskConfig> findTaskList() throws Exception;
    boolean deleteTask(String taskId) throws Exception;
    boolean saveTask(TaskBase taskBase) throws Exception;
    boolean updateTask(TaskUpdate taskUpdate) throws Exception;
    SooPage<SysTaskConfig> findTaskPage(Long pageIndex, Long pageSize, String taskName) throws Exception;
    boolean pauseTaskJob(String taskId) throws Exception;
    boolean runTaskJob(String taskId) throws Exception;
}
