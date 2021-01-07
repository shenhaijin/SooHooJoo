package com.example.soo.service.impl;

import com.example.soo.bean.entity.SysMenu;
import com.example.soo.exception.ParamException;
import com.example.soo.exception.SooException;
import com.example.soo.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 15:26
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class SysMenuService {
    @Resource
    SysMenuMapper sysMenuMapper;

    public List<SysMenu> findUserAllMenuList(String userId) throws SooException{
        if(StringUtils.isEmpty(userId)){
            throw new ParamException("用户Id不存在");
        }
        List<SysMenu> sysMenuList = sysMenuMapper.finaSysUserMenuList(userId);
        return sysMenuList;
    }
}
