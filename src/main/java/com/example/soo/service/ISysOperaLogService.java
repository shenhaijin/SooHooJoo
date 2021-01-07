package com.example.soo.service;

import com.example.common.page.SooPage;
import com.example.soo.bean.entity.SysOperaLog;

import java.util.Date;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 18:25
 * @Description TODO
 * @Version 1.0
 **/
public interface ISysOperaLogService {
    int deleteSysOperaLog(Date beforeTime) throws Exception;
    boolean saveSysOperaLog(SysOperaLog sysOperaLog) throws Exception;
    SooPage<SysOperaLog> pageSysOperaLog(Long pageIndex, Long pageSize, String userName) throws Exception;
}
