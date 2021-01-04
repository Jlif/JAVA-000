package tech.jiangchen.redis_pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RedisPubApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RedisPubApplication.class, args);

        MessageConsumer messageConsumer = applicationContext.getBean(MessageConsumer.class);
        Thread consumerThread = new Thread(messageConsumer, "con-t1");
        consumerThread.start();

        MessageProducer messageProducer = applicationContext.getBean(MessageProducer.class);
        Thread producerThread = new Thread(messageProducer, "thread1");
        producerThread.start();
    }

}
