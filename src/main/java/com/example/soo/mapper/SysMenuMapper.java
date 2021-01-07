package com.example.soo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.soo.bean.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author shenhaijin
 * @Date 2021/1/5 15:06
 * @Description TODO
 * @Version 1.0
 **/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    @Select("select * from sys_menu where id in (select menu_id from sys_role_menu where role_id in (select role_id from sys_user_role where user_id = #{userId}))")
    List<SysMenu> finaSysUserMenuList(@Param("userId") String userId);
}
