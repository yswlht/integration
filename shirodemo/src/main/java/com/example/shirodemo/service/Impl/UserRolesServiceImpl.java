package com.example.shirodemo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shirodemo.model.UserRoles;
import com.example.shirodemo.mapper.UserRolesMapper;
import com.example.shirodemo.service.UserRolesService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserRolesServiceImpl extends ServiceImpl<UserRolesMapper, UserRoles> implements UserRolesService {
    @Override
    public Set<UserRoles> findRolesByUsername(String username) {
        return baseMapper.findRolesByUsername(username);
    }
}
