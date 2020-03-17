package com.zr.redisdemo.mapper;

import com.zr.redisdemo.bean.Player;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerDao {
    int insert(Player player);

    List<Player> selectAll();

    Player selectById(Integer id);

    int update(Player record);

    int deleteById(Integer id);

    void deleteAll();
}
