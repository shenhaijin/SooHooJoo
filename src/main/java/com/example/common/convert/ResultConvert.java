package com.example.common.convert;

import com.example.common.page.SooPage;

import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/11 8:55
 * @Description 数据库对象装DTO对象
 * @Version 1.0
 **/
public interface ResultConvert<S,T> {
    <T> T convert(S source);
    List<T> convert(List<S> sourceList);
    SooPage<T> convert(SooPage<S> sourcePage);
}
