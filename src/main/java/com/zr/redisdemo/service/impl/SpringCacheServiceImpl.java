package com.zr.redisdemo.service.impl;

import com.zr.redisdemo.bean.Player;
import com.zr.redisdemo.mapper.PlayerDao;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author zhangr
 * 2020/3/17 17:57
 */
@Service
@CacheConfig(cacheNames = "playerInfoCache") // 本类内方法指定使用缓存时，默认的名称就是playerInfoCache
@Transactional(rollbackFor = Exception.class)
public class SpringCacheServiceImpl {
    @Resource
    private PlayerDao dao;


    /**
     * 语法说明：
     * #p0表示第一个参数
     */

    //查询
    // @Cacheable 会先查询缓存，如果缓存中存在，则不执行方法
    @Cacheable(key = "#p0", unless = "#result == null ")
    public Player selectById(Integer id) {
        System.out.printf("查询id=%s的运动员", id);
        Assert.notNull(id, "id不能为空");
        return dao.selectById(id);
    }

    //插入
    // 因为必须要有返回值，才能保存到数据库中,否则没数据放到缓存中。
    // 如果保存的对象的某些字段是需要数据库生成的，那保存对象到数据库的时候，就没必要放到缓存了
    @CachePut(key = "#p0.id")
    //必须要有返回值，
    public Player insert(Player player) {
        dao.insert(player);
        return dao.selectById(player.getId());
    }


    //更新
    //可能只是更新某几个字段而已，所以查次数据库把数据全部拿出来全部
    @CachePut(key = "#p0.id")
    public Player update(Player player) {
        dao.update(player);
        return dao.selectById(player.getId());
    }

    //删除缓存名称为playerInfoCache,key等于指定的id对应的缓存
    @CacheEvict(key = "#p0")
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    //清空缓存名称为playerInfoCache（看类名上的注解)下的所有缓存
    //如果数据失败了，缓存时不会清除的
    @CacheEvict(allEntries = true)
    public void deleteAll() {
        dao.deleteAll();
    }

//    @Cacheable(value = "UserInfoList", keyGenerator = "simpleKeyGenerator")
//    public Player findByIdTtl(String id) {
//        System.err.println("根据id=" + id + "获取用户对象，从数据库中获取");
//        Assert.notNull(id, "id不用为空");
//        return this.userMapper.find(id);
//    }
}
