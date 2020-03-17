package com.zr.redisdemo.controller;

import com.zr.redisdemo.bean.Player;
import com.zr.redisdemo.service.impl.SpringCacheServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api
@RestController
@RequestMapping("cache")
public class SpringCacheTestController {
    @Resource
    private SpringCacheServiceImpl cacheService;

    @GetMapping("select/{id}")
    @ApiOperation("select")
    public Player selectAll(@PathVariable Integer id) {
        return cacheService.selectById(id);
    }
}
