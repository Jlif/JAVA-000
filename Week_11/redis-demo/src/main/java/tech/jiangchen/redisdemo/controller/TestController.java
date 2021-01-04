package tech.jiangchen.redisdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jiangchen.redisdemo.aop.RedisCount;
import tech.jiangchen.redisdemo.aop.RedisLock;

/**
 * @author jiangchen
 * @date 2021/01/04
 */
@RestController
public class TestController {

    @RedisLock(key = "redis_lock")
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    //    @RedisCount(key = "redis_count", times = 5)
    @RedisCount(key = "redis_count", times = 5, isExpire = true)
    @GetMapping("/count")
    public String count() {
        return "count";
    }

}
