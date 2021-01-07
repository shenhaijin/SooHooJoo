package com.example.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author          shenhaijin
 * @Date            2020/12/21 14:05
 * @Description     请求响应结果对象
 * @Version         1.0
 **/
@ApiModel
public class Result<T> {
    @ApiModelProperty(name = "版本")
    private String version = "1.0";
    /**
     * 状态,与ExceptionCode枚举 exceCode一致
     */
    @ApiModelProperty(name="请求状态码")
    private String code;
    /**
     * 非ExceptionCode.success时的错误信息
     */
    @ApiModelProperty(name="请求异常信息")
    private String message;
    /**
     * ExceptionCode.success 返回的数据对象
     */
    @ApiModelProperty(name="请求返回结果")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
}
