package com.zr.redisdemo.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * Description:
 * 排行榜Service
 *
 * @author zhangr
 * 2020/3/26 13:45
 */
public interface RankListService {
    /**
     * 添加数据
     */
    void rankAdd(String uid, Integer score);

    /**
     * 增加分数
     */
    void incrScore(String uid, Integer score);

    /**
     * 获取排名
     */
    Long rankNum(String uid);

    /**
     * 获取列表
     */
    Set<ZSetOperations.TypedTuple<Object>> rankWithScore(Integer start, Integer end);
}
