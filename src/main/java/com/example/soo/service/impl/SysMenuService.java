package com.example.soo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.soo.bean.entity.SysMenu;
import com.example.soo.bean.query.SysMenuBase;
import com.example.soo.exception.ParamException;
import com.example.soo.exception.ResultException;
import com.example.soo.exception.SooException;
import com.example.soo.mapper.SysMenuMapper;
import com.example.soo.service.ISysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 15:26
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class SysMenuService implements ISysMenuService {
    @Resource
    SysMenuMapper sysMenuMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addMenu(SysMenuBase sysMenuBase) throws Exception {
        if(ObjectUtils.isEmpty(sysMenuBase)){
            throw new ParamException("菜单为空！");
        }
        String menuName = sysMenuBase.getMenuName();
        String menuPath = sysMenuBase.getMenuPath();
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_name",menuName);
        queryWrapper.or();
        queryWrapper.eq("menu_path",menuPath);
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(queryWrapper);
        if(!CollectionUtils.isEmpty(sysMenuList)){
            throw new ResultException("菜单名或菜单路径已存在！");
        }
        SysMenu sysMenu = sysMenuBase.covertToSysMenu();
        sysMenu.setCreateTime(new Date());
        sysMenu.setUpdateTime(sysMenu.getCreateTime());
        int number = sysMenuMapper.insert(sysMenu);
        if(number < 1){
            throw new ResultException("保存菜单失败！");
        }
        return true;
    }

    @Override
    public List<SysMenu> findUserAllMenuList(String userId) throws SooException{
        if(StringUtils.isEmpty(userId)){
            throw new ParamException("用户Id不存在");
        }
        List<SysMenu> sysMenuList = sysMenuMapper.finaSysUserMenuList(userId);
        return sysMenuList;
    }
}
