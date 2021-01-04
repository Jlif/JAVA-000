package tech.jiangchen.redisdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import tech.jiangchen.redisdemo.utils.JedisUtil;

import javax.annotation.Resource;

/**
 * @author jiangchen
 * @date 2021/01/04
 */
@Slf4j
@Aspect
@Component
public class RedisCountAspect {

    @Resource
    JedisUtil jedisUtil;

    @Pointcut("@annotation(tech.jiangchen.redisdemo.aop.RedisCount)")
    public void point() {

    }

    @Before("point() && @annotation(redisCount)")
    public void before(JoinPoint joinPoint, RedisCount redisCount) throws Exception {
        String key = redisCount.key();
        boolean expire = redisCount.isExpire();
        int leaseTime = redisCount.leaseTime();
        Jedis jedis = jedisUtil.getJedis();
        if (expire) {
            long count = jedis.incrBy(key, 1);
            if (count == 1) {
                jedis.expire(key, leaseTime);
            }
            if (count > redisCount.times()) {
                throw new Exception("超过调用频率，请稍后再试");
            }
        } else {
            long count = jedis.incrBy(key, 1);
            if (count > redisCount.times()) {
                throw new Exception("本次活动结束");
            }
        }
    }

}
