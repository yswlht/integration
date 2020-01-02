package com.example.shirodemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shirodemo.model.Users;

public interface UserService extends IService<Users> {

    Users findUserByUsername(QueryWrapper<Users> qw);
}
