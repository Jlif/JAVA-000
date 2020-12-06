package tech.jiangchen.shardingjdbcdemo.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import tech.jiangchen.shardingjdbcdemo.entity.Order;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Order order) {
        jdbcTemplate.execute("insert into t_order values ("+order.getOrderId()+",1,1,99,'上海',0,now(),0,null,null)");
        return 1;
    }

    @Override
    public Order selectById(int id) {
        String sql = "select * from t_order where order_id = ?";
        RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
