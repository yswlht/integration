package com.example.shirodemo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shirodemo.model.Users;
import com.example.shirodemo.mapper.UserMapper;
import com.example.shirodemo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {
    @Override
    public Users findUserByUsername(QueryWrapper<Users> qw) {
        return baseMapper.findUserByUsername(qw);
    }
}
