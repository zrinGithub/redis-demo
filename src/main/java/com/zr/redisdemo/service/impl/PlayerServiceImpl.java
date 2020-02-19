package com.zr.redisdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zr.redisdemo.bean.Player;
import com.zr.redisdemo.mapper.PlayerDao;
import com.zr.redisdemo.service.PlayerService;
import com.zr.redisdemo.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
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
}
