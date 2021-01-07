package com.example.soo.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 12:03
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel("新增任务对象")
public class TaskBase {
    @ApiModelProperty("任务名称")
    protected String taskName;
    @ApiModelProperty("任务组")
    protected String taskGroup;
    @ApiModelProperty("任务类全路径名")
    protected String taskClass;
    @ApiModelProperty("触发器表达式")
    protected String taskCron;
    @ApiModelProperty("启用状态 1：启用 0：禁用")
    protected String taskStatus;
    @ApiModelProperty("任务描述")
    protected String taskExplain;

    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskGroup() {
        return taskGroup;
    }
    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }
    public String getTaskClass() {
        return taskClass;
    }
    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }
    public String getTaskCron() {
        return taskCron;
    }
    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }
    public String getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
    public String getTaskExplain() {
        return taskExplain;
    }
    public void setTaskExplain(String taskExplain) {
        this.taskExplain = taskExplain;
    }
    @Override
    public String toString() {
        return "TaskUpdate{" +
                "taskName='" + taskName + '\'' +
                ", taskGroup='" + taskGroup + '\'' +
                ", taskClass='" + taskClass + '\'' +
                ", taskCron='" + taskCron + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", taskExplain='" + taskExplain + '\'' +
                '}';
    }
}
