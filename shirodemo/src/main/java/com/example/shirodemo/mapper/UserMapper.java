package com.example.shirodemo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.shirodemo.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<Users> {

    @Select({"<script>","select username, password, password_salt as passwordSalt from users ${ew.customSqlSegment}","</script>"})
    Users findUserByUsername(@Param(Constants.WRAPPER) Wrapper query);
}
