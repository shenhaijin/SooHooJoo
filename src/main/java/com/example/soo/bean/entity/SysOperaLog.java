package com.example.soo.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author shenhaijin
 * @Date 2021/1/4 14:35
 * @Description TODO
 * @Version 1.0
 **/
@TableName("sys_opera_log")
@ApiModel("系统操作日志对象")
public class SysOperaLog implements Serializable {
    @TableId(type = IdType.UUID)
    private String id;
    @TableField("operator")
    @ApiModelProperty("操作者")
    private String operator;
    @TableField("method_name")
    @ApiModelProperty("方法名")
    private String methodName;
    @TableField("class_name")
    @ApiModelProperty("类名")
    private String className;
    @TableField("visit_ip")
    @ApiModelProperty("访问者IP")
    private String visitIp;
    @TableField("create_time")
    @ApiModelProperty("访问时间")
    private Date createTime;
    @TableField("status")
    @ApiModelProperty("操作状态")
    private String status;
    @TableField("takeup_time")
    @ApiModelProperty("响应时间")
    private Long takeupTime;
    @TableField("referer")
    @ApiModelProperty("访问来源")
    private String referer;
    @TableField("user_id")
    @ApiModelProperty("用户Id")
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getVisitIp() {
        return visitIp;
    }

    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getReferer() {
        return referer;
    }
    public void setReferer(String referer) {
        this.referer = referer;
    }
    public Long getTakeupTime() {
        return takeupTime;
    }
    public void setTakeupTime(Long takeupTime) {
        this.takeupTime = takeupTime;
    }
    @Override
    public String toString() {
        return "SysOperaLog{" +
                "id='" + id + '\'' +
                ", operator='" + operator + '\'' +
                ", methodName='" + methodName + '\'' +
                ", className='" + className + '\'' +
                ", visitIp='" + visitIp + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                ", referer='" + referer + '\'' +
                ", userId='" + userId + '\'' +
                ", takeupTime=" + takeupTime +
                '}';
    }
}
