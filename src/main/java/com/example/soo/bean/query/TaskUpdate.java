package com.example.soo.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 11:30
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel("编辑任务对象")
public class TaskUpdate extends TaskBase{
    @ApiModelProperty("主键Id")
    private String taskId;
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    @Override
    public String toString() {
        return "TaskUpdate{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskGroup='" + taskGroup + '\'' +
                ", taskClass='" + taskClass + '\'' +
                ", taskCron='" + taskCron + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", taskExplain='" + taskExplain + '\'' +
                '}';
    }
}
