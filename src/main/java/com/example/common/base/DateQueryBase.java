package com.example.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2020/12/22 11:57
 * @Description 时间查询条件
 * @Version 1.0
 **/
@ApiModel
public class DateQueryBase {
    @ApiModelProperty(value = "开始日期")
    protected String startDate;
    @ApiModelProperty(value = "结束日期")
    protected String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
