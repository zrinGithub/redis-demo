package com.zr.redisdemo.service.impl;

import com.zr.redisdemo.service.RankListService;
import com.zr.redisdemo.util.RedisUtil;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Description:
 *
 * @author zhangr
 * 2020/3/26 13:46
 */
@Service
public class RankListServiceImpl implements RankListService {
    private static final String RANK_KEY = "player_height";

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void rankAdd(String uid, Integer score) {
        redisUtil.zAdd(RANK_KEY, uid, score);
    }

    @Override
    public void incrScore(String uid, Integer score) {
        redisUtil.incrementScore(RANK_KEY, uid, score);
    }

    @Override
    public Long rankNum(String uid) {
        return redisUtil.zRank(RANK_KEY, uid);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> rankWithScore(Integer start, Integer end) {
        return redisUtil.zRankWithScore(RANK_KEY, start, end);
    }
}
