package tech.jiangchen.ssproxydemo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
}
