package com.example.shirodemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shirodemo.model.UserRoles;

import java.util.Set;

public interface UserRolesService extends IService<UserRoles> {

    Set<UserRoles> findRolesByUsername(String username);
}
