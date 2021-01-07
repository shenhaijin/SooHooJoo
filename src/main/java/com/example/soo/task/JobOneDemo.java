package com.example.soo.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author shenhaijin
 * @Date 2021/1/5 8:52
 * @Description TODO
 * @Version 1.0
 **/
public class JobOneDemo implements Job {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("JobOneDemo >>> 开始执行 : " + SIMPLE_DATE_FORMAT.format(new Date()));
    }
}
