package com.example.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2020/12/22 11:58
 * @Description 排序字段查询
 * @Version 1.0
 **/
@ApiModel
public class OrderQueryBase {
    @ApiModelProperty(value = "排序字段")
    protected String field;
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
}
