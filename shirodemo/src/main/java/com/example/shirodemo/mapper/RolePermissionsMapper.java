package com.example.shirodemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shirodemo.model.RolePermissions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RolePermissionsMapper extends BaseMapper<RolePermissions> {

    @Select("select role_name as roleName, permission from roles_permissions where role_name = #{roleName}")
    Set<RolePermissions> findPermissionByRoleName(String roleName);
}
