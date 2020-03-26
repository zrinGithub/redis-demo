package com.zr.redisdemo.controller;

import com.zr.redisdemo.service.RankListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * 排行榜接口
 *
 * @author zhangr
 * 2020/3/26 13:45
 */
@Api
@RestController
@RequestMapping("rank")
public class RankListController {
    @Resource
    private RankListService rankListService;

    @GetMapping("addRank")
    @ApiOperation("addRank")
    public String addRank(@RequestParam("uid") String uid, @RequestParam("score") Integer score) {
        rankListService.rankAdd(uid, score);
        return "success";
    }

    @GetMapping("incrScore")
    @ApiOperation("incrScore")
    public String incrScore(@RequestParam("uid") String uid, @RequestParam("score") Integer score) {
        rankListService.incrScore(uid, score);
        return "success";
    }

    @GetMapping("getRank")
    @ApiOperation("getRank")
    public Long rank(@RequestParam("uid") String uid) {
        return rankListService.rankNum(uid);
    }

    @GetMapping("scoreByRange")
    @ApiOperation("scoreByRange")
    public Set<ZSetOperations.TypedTuple<Object>> scoreByRange(Integer start, Integer end) {
        return rankListService.rankWithScore(start, end);
    }
}
