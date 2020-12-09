package tech.jiangchen.ssproxydemo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService{

    @Resource
    JdbcTemplate jdbcTemplate;

    public void batchInsert(){

    }
}
