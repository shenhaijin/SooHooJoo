package com.example.soo.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 8:54
 * @Description TODO
 * @Version 1.0
 **/
public class JobTwoDemo implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("JobTwoDemo >>> 开始执行 " + System.currentTimeMillis());
        System.out.println("JobTwoDemo >>> 开始执行 " + jobExecutionContext.getJobDetail().getJobClass());
    }
}
