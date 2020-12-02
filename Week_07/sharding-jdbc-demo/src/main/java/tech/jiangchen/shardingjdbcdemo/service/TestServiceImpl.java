package tech.jiangchen.shardingjdbcdemo.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{
    @Override
    public String doSth() {

//        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, configurations, properties);

        return "OK";
    }
}
