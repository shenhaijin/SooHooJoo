package com.example.common.page;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * @Author shenhaijin
 * @Date 2021/1/22 9:05
 * @Description TODO
 * @Version 1.0
 **/
public class PageHelper<T> {
    @ApiModelProperty("当前页")
    private Long pageIndex;
    @ApiModelProperty("每页大小")
    private Long pageSize;
    @ApiModelProperty("总页数")
    private Long pageNo;
    @ApiModelProperty("记录总大小")
    private Long totalSize;
    @ApiModelProperty("分页记录列表")
    private List<T> recordList;

    public Long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        PageHelper<?> pageHelper = (PageHelper<?>) o;
        return Objects.equals(pageIndex, pageHelper.pageIndex) &&
                Objects.equals(pageSize, pageHelper.pageSize) &&
                Objects.equals(pageNo, pageHelper.pageNo) &&
                Objects.equals(totalSize, pageHelper.totalSize) &&
                Objects.equals(recordList, pageHelper.recordList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageIndex, pageSize, pageNo, totalSize, recordList);
    }

    @Override
    public String toString() {
        return "SooPage{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", totalSize=" + totalSize +
                ", recordList=" + recordList +
                '}';
    }
}
