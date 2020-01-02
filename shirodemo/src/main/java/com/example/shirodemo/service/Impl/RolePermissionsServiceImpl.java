package com.example.shirodemo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shirodemo.model.RolePermissions;
import com.example.shirodemo.mapper.RolePermissionsMapper;
import com.example.shirodemo.service.RolePermissionsService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RolePermissionsServiceImpl extends ServiceImpl<RolePermissionsMapper, RolePermissions> implements RolePermissionsService {

    @Override
    public Set<RolePermissions> findPermissionByRoleName(String roleName) {
        return baseMapper.findPermissionByRoleName(roleName);
    }
}
