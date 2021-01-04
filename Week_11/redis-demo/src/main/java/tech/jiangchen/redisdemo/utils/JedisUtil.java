package tech.jiangchen.redisdemo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import tech.jiangchen.redisdemo.config.RedisProperties;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangchen
 * @date 2021/01/04
 */
@Slf4j
@Component
public class JedisUtil {

    @Resource
    private RedisProperties redisProperties;

    private Map<String, JedisPool> map = new ConcurrentHashMap<>();

    private JedisPool getPool() {
        String key = redisProperties.getHost() + ":" + redisProperties.getHost();
        JedisPool pool;
        if (!map.containsKey(key)) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(redisProperties.getMax_idle());
            config.setMaxWaitMillis(redisProperties.getMax_wait());
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);

            pool = new JedisPool(config, redisProperties.getHost(), redisProperties.getPort(), redisProperties.getTimeout(),
                    StringUtils.isBlank(redisProperties.getPassword()) ? null : redisProperties.getPassword());
            map.put(key, pool);
        } else {
            pool = map.get(key);
        }
        return pool;
    }

    public Jedis getJedis() {
        Jedis jedis = null;
        int count = 0;
        do {
            try {
                jedis = getPool().getResource();
                count++;
            } catch (Exception e) {
                log.error("get jedis failed ", e);
            }
        } while (jedis == null && count < redisProperties.getRetry_num());
        return jedis;
    }
}
