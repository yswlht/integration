package com.example.shirodemo.controller;

import com.example.shirodemo.common.HttpResult;
import com.example.shirodemo.model.Users;
import com.example.shirodemo.util.RedisUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {

    public static int ExpireTime = 60;

    @Resource
    private RedisUtil redisUtil;

    @ApiOperation(value = "redis设置接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "储存的值的索引值", dataType = "String", required = true),
            @ApiImplicitParam(name = "value", value = "储存的值", dataType = "String", required = true)
    })
    @PostMapping("/set")
    public HttpResult<Object> redisSet(@RequestParam("key") String key, @RequestParam("value") String value){
        if(redisUtil.set(key, value)){
            return HttpResult.ok();
        }else{
            return HttpResult.error("设置失败");
        }
    }

    @ApiOperation(value = "redis储存Users对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "储存的索引值", dataType = "String", required = true)
    })
    @PostMapping("/setObject")
    public HttpResult<Object> setObject(@RequestParam(value = "key", required = true) String key, @RequestBody(required = true) Users users){
        if(redisUtil.set(key, users)){
            return HttpResult.ok();
        }else{
            return HttpResult.error("设置失败");
        }
    }

    @ApiOperation(value = "获取储存在redis的值", notes = "")
    @GetMapping("/get")
    public HttpResult<Object> redisGet(@ApiParam("请求的键名") @RequestParam("key") String key){
        return HttpResult.ok(redisUtil.get(key));
    }

    @ApiOperation(value = "给redis中储存的值设置过期时间，时间为60s", notes = "")
    @ApiImplicitParam(name = "key", value = "设置过期时间的键", dataType = "String")
    @PutMapping("/expire")
    public HttpResult<Object> expire(@RequestParam("key") String key){
        if(redisUtil.expire(key, ExpireTime)){
            return HttpResult.ok();
        }else{
            return HttpResult.error("设置失败");
        }
    }
}
