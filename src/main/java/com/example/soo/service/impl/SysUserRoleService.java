package com.example.soo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.soo.bean.entity.SysUser;
import com.example.soo.bean.entity.SysUserRole;
import com.example.soo.bean.query.UserRoleUpdate;
import com.example.soo.exception.ParamException;
import com.example.soo.exception.ResultException;
import com.example.soo.mapper.SysUserMapper;
import com.example.soo.mapper.SysUserRoleMapper;
import com.example.soo.service.ISysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author shenhaijin
 * @Date 2021/1/6 20:06
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class SysUserRoleService extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Resource
    SysUserMapper sysUserMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUserRole(UserRoleUpdate userRoleUpdate) throws Exception {
        validateUserRole(userRoleUpdate);
        saveCommon(userRoleUpdate.getRoleIdList(),userRoleUpdate.getUserId());
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserRole(UserRoleUpdate userRoleUpdate) throws Exception {
        validateUserRole(userRoleUpdate);
        UpdateWrapper<SysUserRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",userRoleUpdate.getUserId());
        this.remove(updateWrapper);
        saveCommon(userRoleUpdate.getRoleIdList(),userRoleUpdate.getUserId());
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUserRole(String userRoleId) throws Exception {
        if(!StringUtils.isEmpty(userRoleId)){
            this.removeById(userRoleId);
        }
        return true;
    }

    private void validateUserRole(UserRoleUpdate userRoleUpdate) throws Exception{
        if(ObjectUtils.isEmpty(userRoleUpdate)){
            throw new ParamException("用户角色对象为空！");
        }
        if(CollectionUtils.isEmpty(userRoleUpdate.getRoleIdList())){
            throw new ParamException("角色列表为空！");
        }
        String userId = userRoleUpdate.getUserId();
        SysUser sysUser = sysUserMapper.selectById(userId);
        if(ObjectUtils.isEmpty(sysUser)){
            throw new ResultException("用户不存在");
        }
    }
    private void saveCommon(List<String> roleIdList, String userId) throws Exception{
        List<SysUserRole> sysUserRoleList = new ArrayList<>();
        roleIdList.stream().forEach(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(userId);
            sysUserRoleList.add(sysUserRole);
        });
        boolean saveResult = this.saveBatch(sysUserRoleList);
        if(!saveResult){
            throw new ResultException("保存用户角色信息失败！");
        }
    }
}
