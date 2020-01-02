package com.example.shirodemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shirodemo.common.HttpResult;
import com.example.shirodemo.model.Users;
import com.example.shirodemo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "登录", notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "用户名", dataType = "String"),
        @ApiImplicitParam(name = "password", value = "密码", dataType = "String")
    })
    @GetMapping("/doLogin")
    public HttpResult<Object> doLogin(@RequestParam("username") String username, @RequestParam("password") String password){
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.eq("username", username);
        Users user = userService.findUserByUsername(qw);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, new Md5Hash(password, user.getPasswordSalt(), 1).toHex()));
            return HttpResult.ok("登录成功");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "欢迎", notes = "测试shiro拦截功能")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @ApiOperation(value = "提示登录", notes = "shiro拦截后显示的接口")
    @GetMapping("/login")
    public HttpResult<Object> login(){
        return HttpResult.error("please login");
    }

    @ApiOperation(value = "测试用户角色", notes = "")
    @GetMapping("/check")
    public String check(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("role1")){
            return "有role1权限";
        }else{
            return "没有role1权限";
        }
    }

    @ApiOperation(value = "登出接口", notes = "")
    @GetMapping("/logout")
    public HttpResult<Object> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return HttpResult.ok("登出成功");
    }

    @GetMapping("/test")
    public HttpResult<Users> test(@RequestBody Users users){
        return HttpResult.ok(users);
    }
}