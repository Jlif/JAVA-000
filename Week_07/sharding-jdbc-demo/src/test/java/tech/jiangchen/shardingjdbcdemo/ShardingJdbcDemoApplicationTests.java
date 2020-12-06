package tech.jiangchen.shardingjdbcdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.jiangchen.shardingjdbcdemo.entity.Order;
import tech.jiangchen.shardingjdbcdemo.service.OrderService;

import javax.annotation.Resource;

@SpringBootTest
class ShardingJdbcDemoApplicationTests {

    @Resource
    OrderService orderService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testInsert() {
        Order o = new Order();
        o.setOrderId(1);
        orderService.insert(o);
        Order order = orderService.selectById(1);
        Assertions.assertNotNull(order);
        Assertions.assertEquals(o.getOrderId(), order.getOrderId());
    }

}
