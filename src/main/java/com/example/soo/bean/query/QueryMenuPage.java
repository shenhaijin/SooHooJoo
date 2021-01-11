package com.example.soo.bean.query;

import com.example.common.base.PageQueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2021/1/11 16:31
 * @Description TODO
 * @Version 1.0
 **/
@ApiModel
public class QueryMenuPage extends PageQueryBase {
    @ApiModelProperty("关键字")
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
