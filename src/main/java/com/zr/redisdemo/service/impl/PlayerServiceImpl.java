package com.zr.redisdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zr.redisdemo.bean.Player;
import com.zr.redisdemo.mapper.PlayerDao;
import com.zr.redisdemo.service.PlayerService;
import com.zr.redisdemo.util.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    @Resource
    private PlayerDao dao;

    @Resource
    private RedisUtil redisUtil;

    private static final String PREFIX_PLAYER = "player:detail";

    public List<Player> selectAll() {
        return dao.selectAll();
    }

    public Player selectById(Integer id) {
        Player player = (Player) redisUtil.get(PREFIX_PLAYER + ":" + id);
        if (player == null) {
            player = dao.selectById(id);
            if (player != null) {
                redisUtil.set(PREFIX_PLAYER + ":" + id, player);
                return player;
            }
        }
        return player;
    }

    /**
     * 一级缓存命中，
     * 当Mybatis整合Spring后，直接通过Spring注入Mapper的形式，
     * 如果不是在同一个事务中每个Mapper的每次查询操作都对应一个全新的SqlSession实例，这个时候就不会有一级缓存的命中，
     * 但是在同一个事务中时共用的是同一个SqlSession。
     */
    public void testCache(Integer id) {
        Player player1 = dao.selectById(id);
        System.out.println(JSON.toJSONString(player1));
        Player player2 = dao.selectById(id);
        System.out.println(JSON.toJSONString(player2));

    }
}
