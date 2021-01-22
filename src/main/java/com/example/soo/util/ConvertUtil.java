package com.example.soo.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.page.PageHelper;

/**
 * @Author shenhaijin
 * @Date 2020/12/31 15:03
 * @Description 转换工具类
 * @Version 1.0
 **/
public class ConvertUtil {
    private ConvertUtil(){}
//    public static <T> SooPage<T> mybatisConvertPage(IPage<T> mybatisPage){
//        SooPage<T> sooPage = new SooPage<>();
//        sooPage.setTotalSize(mybatisPage.getTotal());
//        sooPage.setPageNo(mybatisPage.getPages());
//        sooPage.setPageIndex(mybatisPage.getCurrent());
//        sooPage.setRecordList(mybatisPage.getRecords());
//        sooPage.setPageSize(mybatisPage.getSize());
//        return sooPage;
//    }
    public static <T> PageHelper<T> mybatisPageConvertPageHelper(IPage<T> mybatisPage) throws Exception{
        PageHelper<T> pageHelper = new PageHelper<>();
        pageHelper.setTotalSize(mybatisPage.getTotal());
        pageHelper.setPageNo(mybatisPage.getPages());
        pageHelper.setPageIndex(mybatisPage.getCurrent());
        pageHelper.setRecordList(mybatisPage.getRecords());
        pageHelper.setPageSize(mybatisPage.getSize());
        return pageHelper;
    }
}
