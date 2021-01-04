package tech.jiangchen.redis_pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @author jiangchen
 * @date 2021/01/04
 */
@Slf4j
@Component
public class MessageProducer extends Thread {
    public static final String CHANNEL_KEY = "channel:1";

    @Resource
    JedisPoolUtil jedisPoolUtil;

    @Override
    public synchronized void run() {
        putMessage("message test");
    }

    public void putMessage(String message) {
        Jedis jedis = jedisPoolUtil.getJedis();
        Long publish = jedis.publish(CHANNEL_KEY, message);//返回订阅者数量
        log.info(Thread.currentThread().getName() + " - put message[{}],subscriberNum=[{}]", message, publish);

        try {
            Thread.sleep(2000);
            jedis.publish(CHANNEL_KEY, "exit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
