package com.zr.redisdemo.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

/**
 * Description:
 *
 * @author zhangr
 * 2020/4/2 18:54
 */
@Service
@Slf4j
public class LockTest {
    @Value("${customisation.redis.key.printDate}")
    private String printDateRedisKey;

    @Resource
    private RedisTemplate redisTemplate;

    private DefaultRedisScript<Boolean> script;

    @Scheduled(cron = "0/5 * * * * ?")
    public void printDate() {
        String value = UUID.randomUUID().toString();
        try {
            if (getLockWithLuaScript(printDateRedisKey, value, 10L)) {
                log.info("---------Date:" + LocalDateTime.now() + "---------");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            releaseLockWithLuaScript(printDateRedisKey, value);
        }
    }

    //使用redisTemplate自带的函数
    public boolean getLock(String key, String value, long timeout) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(timeout));
    }

    //使用lua脚本获取锁
    public Boolean getLockWithLuaScript(String key, String value, long timeout) {
        script = new DefaultRedisScript<>();
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("getLock.lua")));
        script.setResultType(Boolean.class);

        return (Boolean) redisTemplate.execute(script, Collections.singletonList(key), value, timeout);
    }

    //使用lua脚本释放锁
    public boolean releaseLockWithLuaScript(String key, String value) {
        script = new DefaultRedisScript<>();
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("releaseLock.lua")));
        script.setResultType(Boolean.class);

        return (Boolean) redisTemplate.execute(script, Collections.singletonList(key), value);
    }
}
