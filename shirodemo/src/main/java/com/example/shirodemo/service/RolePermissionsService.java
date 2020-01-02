package com.example.shirodemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shirodemo.model.RolePermissions;

import java.util.Set;

public interface RolePermissionsService extends IService<RolePermissions> {

    Set<RolePermissions> findPermissionByRoleName(String roleName);
}
