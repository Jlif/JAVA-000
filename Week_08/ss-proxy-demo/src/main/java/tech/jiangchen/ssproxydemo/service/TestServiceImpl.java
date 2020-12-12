package tech.jiangchen.ssproxydemo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import tech.jiangchen.ssproxydemo.entity.Order;
import tech.jiangchen.ssproxydemo.entity.OrderMapper;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    JdbcTemplate jdbcTemplate;

    public void batchInsert() {
        for (int i = 0; i < 160; i++) {
            jdbcTemplate.execute("insert into t_order (product_id,user_id,product_price,address,is_delete,create_time,create_by) values (1,1,99,'aaa',0,now(),0)");
        }
        for (int i = 0; i < 160; i++) {
            jdbcTemplate.execute("insert into t_order (product_id,user_id,product_price,address,is_delete,create_time,create_by) values (1,2,88,'aaa',0,now(),0)");
        }
    }

    @Override
    public Order select(Long orderId) {
        String sql = "select * from t_order where order_id = " + orderId;
        return jdbcTemplate.queryForObject(sql, new OrderMapper());
    }
}
