package com.example.soo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.page.SooPage;
import com.example.soo.bean.entity.SysRole;
import com.example.soo.bean.entity.SysRoleMenu;
import com.example.soo.bean.query.QueryRolePage;
import com.example.soo.bean.query.SysRoleMenuBase;
import com.example.soo.bean.query.SysRoleMenuUpdate;
import com.example.soo.exception.ParamException;
import com.example.soo.exception.ResultException;
import com.example.soo.mapper.SysRoleMapper;
import com.example.soo.mapper.SysRoleMenuMapper;
import com.example.soo.service.ISysRoleService;
import com.example.soo.util.ConvertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/6 17:47
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class SysRoleService extends ServiceImpl<SysRoleMenuMapper,SysRoleMenu> implements ISysRoleService {
    @Resource
    SysRoleMapper sysRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRole(SysRoleMenuBase sysRoleMenuBase) throws Exception{
        if(ObjectUtils.isEmpty(sysRoleMenuBase)){
            throw new ParamException("角色对象为空");
        }
        if(CollectionUtils.isEmpty(sysRoleMenuBase.getMenuList())){
            throw new ParamException("菜单列表为空");
        }
        String roleName = sysRoleMenuBase.getRoleName();
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name",roleName);
        List<SysRole> roleList = sysRoleMapper.selectList(queryWrapper);
        if(!CollectionUtils.isEmpty(roleList)){
            throw new ResultException("角色名已存在");
        }
        SysRole sysRole = new SysRole();
        sysRole.setCreateTime(new Date());
        sysRole.setUpdateTime(sysRole.getCreateTime());
        sysRole.setRoleName(roleName);
        int number = sysRoleMapper.insert(sysRole);
        if(number < 1){
            throw new ResultException("保存角色失败！");
        }
        saveCommon(sysRoleMenuBase.getMenuList(),sysRole.getId());
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRole(SysRoleMenuUpdate sysRoleMenuUpdate) throws Exception{
        if(ObjectUtils.isEmpty(sysRoleMenuUpdate)){
            throw new ParamException("编辑角色为空");
        }
        if(CollectionUtils.isEmpty(sysRoleMenuUpdate.getMenuList())){
            throw new ParamException("编辑角色菜单为空");
        }
        SysRole sysRole = sysRoleMapper.selectById(sysRoleMenuUpdate.getRoleId());
        if(ObjectUtils.isEmpty(sysRole)){
            throw new ResultException("无此角色信息");
        }
        if(!StringUtils.isEmpty(sysRoleMenuUpdate.getRoleName())){
            sysRole.setRoleName(sysRoleMenuUpdate.getRoleName());
        }
        sysRole.setUpdateTime(new Date());
        int number = sysRoleMapper.updateById(sysRole);
        if(number < 1){
            throw new ResultException("编辑角色失败！");
        }
        UpdateWrapper<SysRoleMenu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("role_id",sysRoleMenuUpdate.getRoleId());
        this.remove(updateWrapper);
        saveCommon(sysRoleMenuUpdate.getMenuList(),sysRoleMenuUpdate.getRoleId());
        return true;
    }

    @Override
    public SooPage<SysRole> pageRole(QueryRolePage queryRolePage) throws Exception {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(queryRolePage.getRoleName())){
            wrapper.like("role_name",queryRolePage.getRoleName());
        }
        wrapper.orderByDesc("update_time");
        IPage<SysRole> sysRoleIPage = new Page(queryRolePage.getPageIndex(),queryRolePage.getPageSize());
        sysRoleIPage = sysRoleMapper.selectPage(sysRoleIPage,wrapper);
        SooPage<SysRole> sooPage = ConvertUtil.mybatisConvertPage(sysRoleIPage);
        return sooPage;
    }

    @Override
    public boolean deleteRole(String roleId) throws Exception {
        if(!StringUtils.isEmpty(roleId)){
            sysRoleMapper.deleteById(roleId);
            /**
             * 删除菜单关联表记录
             */
            UpdateWrapper<SysRoleMenu> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("role_id",roleId);
            this.remove(updateWrapper);
        }
        return true;
    }

    private void saveCommon(List<String> menuList,String roleId) throws Exception{
        List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
        menuList.stream().forEach(menuId -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenuList.add(sysRoleMenu);
        });
        boolean saveResult = this.saveBatch(sysRoleMenuList);
        if(!saveResult){
            throw new ResultException("保存角色菜单失败！");
        }
    }
}
