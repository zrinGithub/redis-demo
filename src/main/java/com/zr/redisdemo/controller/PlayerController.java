package com.zr.redisdemo.controller;

import com.zr.redisdemo.bean.Player;
import com.zr.redisdemo.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api
@RestController
@RequestMapping("player")
public class PlayerController {
    @Resource
    private PlayerService playerService;

    @GetMapping("all")
    @ApiOperation("all")
    public List<Player> selectAll() {
        return playerService.selectAll();
    }

    @GetMapping("id/{id}")
    @ApiOperation("id")
    public Player selectById(@PathVariable("id") Integer id) {
        return playerService.selectById(id);
    }

}
