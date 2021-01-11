package com.example.soo.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author shenhaijin
 * @Date 2021/1/4 14:35
 * @Description TODO
 * @Version 1.0
 **/
@TableName("sys_opera_log")
@ApiModel("系统操作日志对象")
public class SysOperaLog {
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
    @TableField("operation_time")
    @ApiModelProperty("访问时间")
    private Date operationTime;
    @TableField("operat_status")
    @ApiModelProperty("操作状态")
    private String operatStatus;
    @TableField("handler_time")
    @ApiModelProperty("操作状态")
    private Long handlerTime;

    public Long getHandlerTime() {
        return handlerTime;
    }

    public void setHandlerTime(Long handlerTime) {
        this.handlerTime = handlerTime;
    }

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

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getVisitIp() {
        return visitIp;
    }

    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp;
    }

    public String getOperatStatus() {
        return operatStatus;
    }

    public void setOperatStatus(String operatStatus) {
        this.operatStatus = operatStatus;
    }

    @Override
    public String toString() {
        return "SysOperaLog{" +
                "id='" + id + '\'' +
                ", operator='" + operator + '\'' +
                ", methodName='" + methodName + '\'' +
                ", className='" + className + '\'' +
                ", visitIp='" + visitIp + '\'' +
                ", operationTime=" + operationTime +
                ", operatStatus='" + operatStatus + '\'' +
                ", handlerTime=" + handlerTime +
                '}';
    }
}
