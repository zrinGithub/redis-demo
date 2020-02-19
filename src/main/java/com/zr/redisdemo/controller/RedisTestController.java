package com.zr.redisdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/23 13:10
 */
@Api
@RestController
@RequestMapping("test")
public class RedisTestController {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping
    @ApiOperation("test")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/test/redis")
    @ApiOperation("RedisTestController")
    public String testRedis() {
        redisTemplate.opsForValue().set("name","jerry");
        return redisTemplate.opsForValue().get("name");
    }
}
