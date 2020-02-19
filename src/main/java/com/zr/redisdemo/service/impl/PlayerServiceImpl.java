package com.zr.redisdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zr.redisdemo.bean.Player;
import com.zr.redisdemo.mapper.PlayerDao;
import com.zr.redisdemo.service.PlayerService;
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

    public List<Player> selectAll() {
        return dao.selectAll();
    }

    public Player selectById(Integer id) {
        return dao.selectById(id);
    }
}
