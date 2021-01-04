package tech.jiangchen.redis_pub;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @author jiangchen
 * @date 2021/01/04
 */
@Component
public class MessageConsumer implements Runnable {
    public static final String CHANNEL_KEY = "channel:1";//频道

    public static final String EXIT_COMMAND = "exit";//结束程序的消息

    private MyJedisPubSub myJedisPubSub = new MyJedisPubSub();//处理接收消息

    @Resource
    JedisPoolUtil jedisPoolUtil;

    public void consumerMessage() {
        Jedis jedis = jedisPoolUtil.getJedis();
        jedis.subscribe(myJedisPubSub, CHANNEL_KEY);//第一个参数是处理接收消息，第二个参数是订阅的消息频道
    }

    @Override
    public void run() {
        while (true) {
            consumerMessage();
        }
    }

}
