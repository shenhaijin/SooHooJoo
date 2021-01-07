package com.example.soo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.page.SooPage;
import com.example.soo.bean.entity.SysOperaLog;
import com.example.soo.exception.ResultException;
import com.example.soo.mapper.SysOperaLogMapper;
import com.example.soo.service.ISysOperaLogService;
import com.example.soo.util.ConvertUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author shenhaijin
 * @Date 2021/1/4 14:44
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class SysOperaLogService implements ISysOperaLogService {
    private static long CLEAR_SYSOPERALOG_TIME = 24 * 60 * 60 * 1000;
    @Resource
    SysOperaLogMapper sysOperaLogMapper;
    @Override
    public int deleteSysOperaLog(Date beforeTime) throws Exception{
        if(beforeTime == null){
            beforeTime = new Date(System.currentTimeMillis() - CLEAR_SYSOPERALOG_TIME);
        }
        UpdateWrapper<SysOperaLog> updateWrapper = new UpdateWrapper<SysOperaLog>();
        updateWrapper.le("operation_time",beforeTime);
        int number = sysOperaLogMapper.delete(updateWrapper);
        return number;
    }
    @Override
    public boolean saveSysOperaLog(SysOperaLog sysOperaLog) throws Exception{
        int num = sysOperaLogMapper.insert(sysOperaLog);
        if(num < 1){
            throw new ResultException("保存操作日志失败");
        }
        return true;
    }
    @Override
    public SooPage<SysOperaLog> pageSysOperaLog(Long pageIndex, Long pageSize, String userName) throws Exception{
        QueryWrapper<SysOperaLog> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userName)){
            wrapper.like("operator",userName);
        }
        wrapper.orderByDesc("operation_time");
        IPage<SysOperaLog> sysOperaLogIPage = new Page(pageIndex,pageSize);
        sysOperaLogIPage = sysOperaLogMapper.selectPage(sysOperaLogIPage,wrapper);
        SooPage<SysOperaLog> sooPage = ConvertUtil.mybatisConvertPage(sysOperaLogIPage);
        return sooPage;
    }

}
