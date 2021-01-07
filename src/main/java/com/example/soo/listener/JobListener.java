package com.example.soo.listener;

import com.example.soo.bean.entity.SysTaskConfig;
import com.example.soo.service.impl.SysTaskService;
import com.example.soo.task.JobManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 9:02
 * @Description TODO
 * @Version 1.0
 **/
@Component
public class JobListener implements CommandLineRunner {
    @Autowired
    SysTaskService sysTaskService;
    @Autowired
    JobManager jobManager;
    @Override
    public void run(String... args) throws Exception {
        List<SysTaskConfig> sysTaskConfigList = sysTaskService.findTaskList();
        jobManager.initJob(sysTaskConfigList);
    }
}
