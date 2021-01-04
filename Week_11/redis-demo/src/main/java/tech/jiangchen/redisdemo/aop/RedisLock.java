package tech.jiangchen.redisdemo.aop;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {
    /**
     * 业务键
     *
     * @return
     */
    String key();

    /**
     * 锁的过期秒数,默认是5秒
     *
     * @return
     */
    int expire() default 5000;

    /**
     * 尝试加锁，最多等待时间
     *
     * @return
     */
    long waitTime() default Long.MIN_VALUE;

    /**
     * 锁的超时时间单位
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}

