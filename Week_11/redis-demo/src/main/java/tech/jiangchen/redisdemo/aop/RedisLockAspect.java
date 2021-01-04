package tech.jiangchen.redisdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import tech.jiangchen.redisdemo.utils.JedisUtil;
import tech.jiangchen.redisdemo.utils.RedisLockHelper;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author jiangchen
 * @date 2021/01/04
 */
@Slf4j
@Aspect
@Component
public
class RedisLockAspect {

    @Resource
    private RedisLockHelper redisLockHelper;
    @Resource
    private JedisUtil jedisUtil;

    @Around("@annotation(tech.jiangchen.redisdemo.aop.RedisLock)")
    public Object around(ProceedingJoinPoint joinPoint) {
        Jedis jedis = jedisUtil.getJedis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String value = UUID.randomUUID().toString();
        String key = redisLock.key();
        try {
            final boolean isLock = redisLockHelper.lock(jedis, key, value, redisLock.expire(), redisLock.timeUnit());
            log.info("isLock : {}", isLock);
            if (!isLock) {
                log.error("获取锁失败");
                throw new RuntimeException("获取锁失败");
            }
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        } finally {
            log.info("释放锁");
            redisLockHelper.unlock(jedis, key, value);
            jedis.close();
        }
    }
}
