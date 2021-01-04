package tech.jiangchen.redisdemo.aop;

import java.lang.annotation.*;

/**
 * @author jiangchen
 * @date 2021/01/04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCount {

    /**
     * 计数key
     */
    String key();

    /**
     * 限制频次
     */
    long times();

    /**
     * 是否频控
     */
    boolean isExpire() default false;

    /**
     * 租期 默认 10 秒
     */
    int leaseTime() default 10;

}
