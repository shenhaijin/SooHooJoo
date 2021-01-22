package com.example.soo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.page.PageHelper;
import com.example.soo.bean.entity.SysMenu;
import com.example.soo.bean.query.QueryMenuPage;
import com.example.soo.bean.query.SysMenuBase;
import com.example.soo.bean.query.SysMenuUpdate;
import com.example.soo.exception.ParamException;
import com.example.soo.exception.ResultException;
import com.example.soo.exception.SooException;
import com.example.soo.mapper.SysMenuMapper;
import com.example.soo.service.ISysMenuService;
import com.example.soo.util.ConvertUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

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
        SysMenu sysMenu = sysMenuBase.convert();
        sysMenu.setCreateTime(new Date());
        sysMenu.setUpdateTime(sysMenu.getCreateTime());
        int number = sysMenuMapper.insert(sysMenu);
        if(number < 1){
            throw new ResultException("保存菜单失败！");
        }
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateMenu(SysMenuUpdate sysMenuUpdate) throws Exception {
        if(ObjectUtils.isEmpty(sysMenuUpdate)){
            throw new ParamException("菜单为空");
        }
        if(StringUtils.isEmpty(sysMenuUpdate.getMenuId())){
            throw new ParamException("菜单id为空");
        }
        SysMenu sysMenu = sysMenuMapper.selectById(sysMenuUpdate.getMenuId());
        if(ObjectUtils.isEmpty(sysMenu)){
            throw new ResultException("菜单不存在");
        }

        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id",sysMenuUpdate.getMenuId());
        queryWrapper.and(new Consumer<QueryWrapper<SysMenu>>() {
            @Override
            public void accept(QueryWrapper<SysMenu> sysMenuQueryWrapper) {
                sysMenuQueryWrapper.eq("menu_name",sysMenuUpdate.getMenuName());
                sysMenuQueryWrapper.or();
                sysMenuQueryWrapper.eq("menu_path",sysMenuUpdate.getMenuPath());
            }
        });
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(queryWrapper);
        if(!CollectionUtils.isEmpty(sysMenuList)){
            throw new ResultException("菜单名或路径已存在！");
        }
        if(!StringUtils.isEmpty(sysMenuUpdate.getMenuName())){
            sysMenu.setMenuName(sysMenuUpdate.getMenuName());
        }
        if(!StringUtils.isEmpty(sysMenuUpdate.getMenuPath())){
            sysMenu.setMenuPath(sysMenuUpdate.getMenuPath());
        }
        if(!StringUtils.isEmpty(sysMenuUpdate.getMenuType())){
            sysMenu.setMenuType(sysMenuUpdate.getMenuType());
        }
        sysMenu.setUpdateTime(new Date());
        int number = sysMenuMapper.updateById(sysMenu);
        if(number < 1){
            throw new ResultException("编辑菜单失败！");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteMenu(String menuId) throws Exception {
        if(StringUtils.isEmpty(menuId)){
            throw new ParamException("菜单Id");
        }
        sysMenuMapper.deleteById(menuId);
        return true;
    }

    @Override
    @Cacheable(key = "#userId",unless = "#result == null",cacheNames = "textRedis")
    public List<SysMenu> findUserAllMenuList(String userId) throws SooException{
        if(StringUtils.isEmpty(userId)){
            throw new ParamException("用户Id不存在");
        }
        List<SysMenu> sysMenuList = sysMenuMapper.finaSysUserMenuList(userId);
        return sysMenuList;
    }

    @Override
    public PageHelper<SysMenu> pageMenu(QueryMenuPage queryMenuPage) throws Exception {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(queryMenuPage.getCondition())){
            queryWrapper.like("menu_name",queryMenuPage.getCondition());
            queryWrapper.or();
            queryWrapper.like("menu_path",queryMenuPage.getCondition());
        }
        queryWrapper.orderByDesc("update_time");
        IPage<SysMenu> page = new Page<>(queryMenuPage.getPageIndex(),queryMenuPage.getPageSize());
        page = sysMenuMapper.selectPage(page,queryWrapper);
        PageHelper<SysMenu> sysMenuSooPage = ConvertUtil.mybatisPageConvertPageHelper(page);
        return sysMenuSooPage;
    }
}
