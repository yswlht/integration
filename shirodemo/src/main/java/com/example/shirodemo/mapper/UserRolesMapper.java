package com.example.shirodemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shirodemo.model.UserRoles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface UserRolesMapper extends BaseMapper<UserRoles> {

    @Select("select username, role_name as roleName from user_roles where username = #{username}")
    Set<UserRoles> findRolesByUsername(String username);

}
