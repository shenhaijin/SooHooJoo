package com.example.soo.task;

import com.example.common.constant.RunStatus;
import com.example.soo.bean.entity.SysTaskConfig;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 11:53
 * @Description TODO
 * @Version 1.0
 **/
@Component
public class JobManager {
    private static final Logger logger = LoggerFactory.getLogger(JobManager.class);
    @Autowired
    SchedulerFactoryBean schedulerFactory;

    public void initJob(List<SysTaskConfig> sysTaskConfigList) {
        if(!CollectionUtils.isEmpty(sysTaskConfigList)){
            sysTaskConfigList.stream().forEach(sysTaskConfig -> {
                try {
                    addJob(sysTaskConfig);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            sysTaskConfigList.stream().forEach(sysTaskConfig -> {
                try{
                    if(RunStatus.PAUSE.getCode().equals(sysTaskConfig.getTaskStatus())){
                        pauseJob(sysTaskConfig);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            });
        }
    }
    public boolean addJob(SysTaskConfig sysTaskConfig) throws Exception{
        logger.info(" jobManager[addJob] >>>  sysTaskConfig " + sysTaskConfig);
        Class taskClass = Class.forName(sysTaskConfig.getTaskClass());
        JobDetail jobDetail =
                JobBuilder.newJob(taskClass).withIdentity(sysTaskConfig.getTaskName(),sysTaskConfig.getTaskGroup()).build();

        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        triggerBuilder.withIdentity(sysTaskConfig.getTaskName(), sysTaskConfig.getTaskGroup());
        triggerBuilder.startNow();
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(sysTaskConfig.getTaskCron()));
        CronTrigger trigger = (CronTrigger) triggerBuilder.build();
        schedulerFactory.getScheduler().scheduleJob(jobDetail,trigger);
        logger.info(" jobManager[addJob] >>>  end");
        return true;
    }
    public boolean pauseJob(SysTaskConfig sysTaskConfig) throws Exception{
        logger.info(" jobManager[pauseJob] >>>  sysTaskConfig " + sysTaskConfig);
        if(!ObjectUtils.isEmpty(sysTaskConfig)){
            JobKey jobKey = JobKey.jobKey(sysTaskConfig.getTaskName(),sysTaskConfig.getTaskGroup());
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.pauseJob(jobKey);
        }
        logger.info(" jobManager[pauseJob] >>>  end");
        return true;
    }
    public boolean resumeJob(SysTaskConfig sysTaskConfig) throws Exception{
        logger.info(" jobManager[resumeJob] >>>  sysTaskConfig " + sysTaskConfig);
        if(!ObjectUtils.isEmpty(sysTaskConfig)){
            JobKey jobKey = JobKey.jobKey(sysTaskConfig.getTaskName(),sysTaskConfig.getTaskGroup());
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.resumeJob(jobKey);
        }
        logger.info(" jobManager[resumeJob] >>>  end");
        return true;
    }
    public boolean deleteJob(SysTaskConfig sysTaskConfig) throws Exception{
        logger.info(" jobManager[deleteJob] >>>  sysTaskConfig " + sysTaskConfig);
        if(!ObjectUtils.isEmpty(sysTaskConfig)){
            JobKey jobKey = JobKey.jobKey(sysTaskConfig.getTaskName(),sysTaskConfig.getTaskGroup());
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.deleteJob(jobKey);
        }
        logger.info(" jobManager[deleteJob] >>>  end");
        return true;
    }

    public boolean updateJob(SysTaskConfig oldTask,SysTaskConfig curTask) throws Exception{
        logger.info(" jobManager[updateJob] >>>  oldTask " + oldTask.toString() + " curTask : " + curTask);
        if(!ObjectUtils.isEmpty(curTask) && !ObjectUtils.isEmpty(oldTask)){
            if(!curTask.equals(oldTask)){
                JobKey jobKey = JobKey.jobKey(oldTask.getTaskName(),oldTask.getTaskGroup());
                Scheduler scheduler = schedulerFactory.getScheduler();
                scheduler.deleteJob(jobKey);
                if(RunStatus.RUN.getCode().equals(curTask.getTaskStatus())){
                    addJob(curTask);
                }
            }
        }
        logger.info(" jobManager[updateJob] >>>  end");
        return true;
    }
}
