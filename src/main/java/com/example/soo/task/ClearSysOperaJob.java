package com.example.soo.task;

import com.example.soo.service.impl.SysOperaLogService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 9:58
 * @Description TODO
 * @Version 1.0
 **/
@Component
public class ClearSysOperaJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(ClearSysOperaJob.class);
    @Autowired
    SysOperaLogService sysOperaLogService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("clearSysOperaLog >>>> 开始清理操作日志");
        int number = 0;
        try {
            number = sysOperaLogService.deleteSysOperaLog(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("clearSysOperaLog >>>> 结束清理操作日志,共清理" + number + "条记录！");
    }
}
