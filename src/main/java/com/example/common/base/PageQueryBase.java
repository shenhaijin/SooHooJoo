package com.example.common.base;

import com.example.common.constant.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author shenhaijin
 * @Date 2020/12/22 11:56
 * @Description 分页查询条件
 * @Version 1.0
 **/
@ApiModel
public class PageQueryBase {
    @ApiModelProperty(value = "分页大小")
    protected Integer pageSize = Constant.DEFAULT_PAGE_SIZE;
    @ApiModelProperty(value = "当前页")
    protected Integer pageIndex = Constant.DEFAULT_PAGE_INDEX;
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
