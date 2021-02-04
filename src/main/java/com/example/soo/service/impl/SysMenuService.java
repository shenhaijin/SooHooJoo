package com.example.soo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    private void commonValidate(SysMenuBase sysMenuBase) throws Exception{
        if(ObjectUtils.isEmpty(sysMenuBase)){
            throw new ParamException("菜单为空！");
        }
        if(StringUtils.isEmpty(sysMenuBase.getSystem())){
            throw new ParamException("所属系统为空！");
        }
        if(StringUtils.isEmpty(sysMenuBase.getMenuName())){
            throw new ParamException("菜单名称为空！");
        }
        if(StringUtils.isEmpty(sysMenuBase.getMenuPath())){
            throw new ParamException("菜单路径为空！");
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addMenu(SysMenuBase sysMenuBase) throws Exception {
        commonValidate(sysMenuBase);

        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper = new QueryWrapper<SysMenu>().lambda();
        lambdaQueryWrapper.eq(SysMenu::getSystem,sysMenuBase.getSystem());
        lambdaQueryWrapper.and(new Consumer<LambdaQueryWrapper<SysMenu>>(){
            @Override
            public void accept(LambdaQueryWrapper<SysMenu> sysMenuLambdaQueryWrapper) {
                sysMenuLambdaQueryWrapper.eq(SysMenu::getMenuName,sysMenuBase.getMenuName());
                sysMenuLambdaQueryWrapper.or();
                sysMenuLambdaQueryWrapper.eq(SysMenu::getMenuPath,sysMenuBase.getMenuPath());
            }
        });
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(lambdaQueryWrapper);
        if(!CollectionUtils.isEmpty(sysMenuList)){
            throw new ResultException("菜单名或菜单路径已存在！");
        }
        SysMenu sysMenu = sysMenuBase.convert();
        int number = sysMenuMapper.insert(sysMenu);
        if(number < 1){
            throw new ResultException("保存菜单失败！");
        }
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateMenu(SysMenuUpdate sysMenuUpdate) throws Exception {
        commonValidate(sysMenuUpdate);

        if(StringUtils.isEmpty(sysMenuUpdate.getMenuId())){
            throw new ParamException("菜单id为空");
        }
        SysMenu sysMenu = sysMenuMapper.selectById(sysMenuUpdate.getMenuId());
        if(ObjectUtils.isEmpty(sysMenu)){
            throw new ResultException("菜单不存在");
        }
        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper = new QueryWrapper<SysMenu>().lambda();
        lambdaQueryWrapper.ne(SysMenu::getId,sysMenuUpdate.getMenuId());
        lambdaQueryWrapper.eq(SysMenu::getSystem,sysMenuUpdate.getSystem());
        lambdaQueryWrapper.and(new Consumer<LambdaQueryWrapper<SysMenu>>() {
            @Override
            public void accept(LambdaQueryWrapper<SysMenu> sysMenuLambdaQueryWrapper) {
                sysMenuLambdaQueryWrapper.eq(SysMenu::getMenuName,sysMenuUpdate.getMenuName());
                sysMenuLambdaQueryWrapper.or();
                sysMenuLambdaQueryWrapper.eq(SysMenu::getMenuPath,sysMenuUpdate.getMenuPath());
            }
        });
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(lambdaQueryWrapper);
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
        if(!StringUtils.isEmpty(sysMenuUpdate.getSystem())){
            sysMenu.setSystem(sysMenuUpdate.getSystem());
        }
        if(!StringUtils.isEmpty(sysMenuUpdate.getParentId())){
            sysMenu.setParentId(sysMenuUpdate.getParentId());
        }
        if(!StringUtils.isEmpty(sysMenuUpdate.getLevel())){
            sysMenu.setLevel(sysMenuUpdate.getLevel());
        }
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
    public List<SysMenu> findUserAllMenuList(String userId) throws Exception{
        if(StringUtils.isEmpty(userId)){
            throw new ParamException("用户Id不存在");
        }
        List<SysMenu> sysMenuList = sysMenuMapper.finaSysUserMenuList(userId);
        return sysMenuList;
    }

    @Override
    public PageHelper<SysMenu> pageMenu(QueryMenuPage queryMenuPage) throws Exception {
        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper = new QueryWrapper<SysMenu>().lambda();
        if(queryMenuPage == null ){
            queryMenuPage = new QueryMenuPage();
        }

        if(!StringUtils.isEmpty(queryMenuPage.getSystem())){
            lambdaQueryWrapper.eq(SysMenu::getSystem,queryMenuPage.getSystem());
        }
        if(!StringUtils.isEmpty(queryMenuPage.getCondition())){
            final String condition = queryMenuPage.getCondition();
            lambdaQueryWrapper.and(new Consumer<LambdaQueryWrapper<SysMenu>>() {
                @Override
                public void accept(LambdaQueryWrapper<SysMenu> sysMenuLambdaQueryWrapper) {
                    sysMenuLambdaQueryWrapper.like(SysMenu::getMenuName,condition);
                    sysMenuLambdaQueryWrapper.or();
                    sysMenuLambdaQueryWrapper.like(SysMenu::getMenuPath,condition);
                }
            });
        }
        lambdaQueryWrapper.orderByDesc(SysMenu::getUpdateTime);
        IPage<SysMenu> page = new Page<>(queryMenuPage.getPageIndex(),queryMenuPage.getPageSize());
        page = sysMenuMapper.selectPage(page,lambdaQueryWrapper);
        PageHelper<SysMenu> sysMenuSooPage = ConvertUtil.mybatisPageConvertPageHelper(page);
        return sysMenuSooPage;
    }
}
