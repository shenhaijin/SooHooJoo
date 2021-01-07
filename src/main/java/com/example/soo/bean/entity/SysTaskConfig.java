package com.example.soo.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

/**
 * @Author shenhaijin
 * @Date 2021/1/4 16:24
 * @Description TODO
 * @Version 1.0
 **/
@TableName("sys_task_config")
@ApiModel("定时任务对象")
public class SysTaskConfig {
    @TableId(type = IdType.UUID)
    private String id;
    @TableField("task_name")
    @ApiModelProperty("任务名称")
    private String taskName;

    @TableField("task_class")
    @ApiModelProperty("任务类全路径名")
    private String taskClass;

    @TableField("task_cron")
    @ApiModelProperty("触发器表达式")
    private String taskCron;

    @TableField("task_status")
    @ApiModelProperty("任务启用状态 1：启用 0：禁用")
    private String taskStatus;

    @TableField("task_explain")
    @ApiModelProperty("任务描述")
    private String taskExplain;

    @TableField("task_group")
    @ApiModelProperty("任务组")
    private String taskGroup;

    @TableField("create_time")
    @ApiModelProperty("任务创建时间")
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty("任务修改时间")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysTaskConfig{" +
                "id='" + id + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskClass='" + taskClass + '\'' +
                ", taskCron='" + taskCron + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", taskExplain='" + taskExplain + '\'' +
                ", taskGroup='" + taskGroup + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysTaskConfig that = (SysTaskConfig) o;
        return Objects.equals(taskName, that.taskName) &&
                Objects.equals(taskClass, that.taskClass) &&
                Objects.equals(taskCron, that.taskCron) &&
                Objects.equals(taskGroup, that.taskGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, taskClass, taskCron, taskGroup);
    }
}
