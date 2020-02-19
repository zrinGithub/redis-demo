package com.zr.redisdemo.service;


import com.zr.redisdemo.bean.Player;

import java.util.List;

public interface PlayerService {
    List<Player> selectAll();
    Player selectById(Integer id);
}
