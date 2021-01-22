package com.example.soo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.page.PageHelper;
import com.example.soo.bean.entity.SysUser;
import com.example.soo.bean.entity.SysUserRole;
import com.example.soo.bean.query.SysUserBase;
import com.example.soo.bean.query.SysUserUpdate;
import com.example.soo.exception.ParamException;
import com.example.soo.exception.ResultException;
import com.example.soo.mapper.SysUserMapper;
import com.example.soo.mapper.SysUserRoleMapper;
import com.example.soo.service.ISysUserService;
import com.example.soo.util.ConvertUtil;
import org.springframework.cache.annotation.CacheEvict;
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
 * @Date 2020/12/21 15:16
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class SysUserService implements ISysUserService {
    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    SysUserRoleMapper sysUserRoleMapper;
    @Override
    public SysUser findOneUser(String userName, String passWord) throws Exception{
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        queryWrapper.eq("pass_word",passWord);
        SysUser User = sysUserMapper.selectOne(queryWrapper);
        return User;
    }

    @Override
    public SysUser sel(String id) throws Exception {
        SysUser user = sysUserMapper.selectById(id);
        if(user == null ){
            throw new ResultException("查询用户不存在");
        }
        return sysUserMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    @CacheEvict(key = "#userId",cacheNames = "textRedis")
    public boolean deleteUser(String userId) throws Exception{
        if(!StringUtils.isEmpty(userId)){
            sysUserMapper.deleteById(userId);
            UpdateWrapper<SysUserRole> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id",userId);
            sysUserRoleMapper.delete(updateWrapper);
        }
        return true;
    }
    @Override
    public List<SysUser> getListUser() throws Exception{
        QueryWrapper<SysUser> userEntityWrapper = new QueryWrapper<>();
        userEntityWrapper.isNotNull("real_name");
        userEntityWrapper.ne("real_name","");
        List<SysUser> userList = sysUserMapper.selectList(userEntityWrapper);
        return userList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveUser(SysUserBase sysUserBase) throws Exception{
        if(ObjectUtils.isEmpty(sysUserBase)){
            throw new ParamException("用户对象为空");
        }
        if(StringUtils.isEmpty(sysUserBase.getUserName())){
            throw new ParamException("用户名为空");
        }
        if(StringUtils.isEmpty(sysUserBase.getPassWord())){
            throw new ParamException("密码为空");
        }
        QueryWrapper<SysUser> userEntityWrapper = new QueryWrapper<>();
        userEntityWrapper.eq("user_name",sysUserBase.getUserName());
        List<SysUser> userList = sysUserMapper.selectList(userEntityWrapper);
        if(!CollectionUtils.isEmpty(userList)){
            throw new ResultException("用户名存在");
        }
        SysUser saveUser = new SysUser();
        saveUser.setPassWord(sysUserBase.getPassWord());
        saveUser.setUserName(sysUserBase.getUserName());
        saveUser.setRealName(sysUserBase.getRealName());
        saveUser.setSuperAdmin(sysUserBase.getSuperAdmin());
        saveUser.setCreateTime(new Date(System.currentTimeMillis()));
        saveUser.setUpdateTime(saveUser.getCreateTime());
        Integer num = sysUserMapper.insert(saveUser);
        if(num < 1){
            throw new ResultException("插入用户失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateUser(SysUserUpdate sysUserUpdate) throws Exception {
        if(ObjectUtils.isEmpty(sysUserUpdate)){
            throw new ParamException("编辑用户对象为空！");
        }
        String userId = sysUserUpdate.getId();
        SysUser sysUser = sysUserMapper.selectById(userId);
        if(ObjectUtils.isEmpty(sysUser)){
            throw new ResultException("用户不存在！");
        }
        if(!StringUtils.isEmpty(sysUserUpdate.getUserName())){
            sysUser.setUserName(sysUserUpdate.getUserName());
        }
        if(!StringUtils.isEmpty(sysUserUpdate.getPassWord())){
            sysUser.setPassWord(sysUserUpdate.getPassWord());
        }
        if(!StringUtils.isEmpty(sysUserUpdate.getRealName())){
            sysUser.setRealName(sysUserUpdate.getRealName());
        }
        if(!StringUtils.isEmpty(sysUserUpdate.getSuperAdmin())){
            sysUser.setSuperAdmin(sysUserUpdate.getSuperAdmin());
        }
        sysUser.setUpdateTime(new Date());
        int number = sysUserMapper.updateById(sysUser);
        if(number < 1){
            throw new ResultException("编辑用户失败！");
        }
        return true;
    }

    @Override
    public PageHelper<SysUser> pageUser(Long pageIndex, Long pageSize, String userName) throws Exception{
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userName)){
            wrapper.like("user_name",userName);
        }
        wrapper.orderByDesc("update_time");
        IPage<SysUser> userPage = new Page(pageIndex,pageSize);
        userPage = sysUserMapper.selectPage(userPage,wrapper);
        PageHelper<SysUser> sooPage = ConvertUtil.mybatisPageConvertPageHelper(userPage);
        return sooPage;
    }
}
