package tech.jiangchen.dynamicdatasource01.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.jiangchen.dynamicdatasource01.config.DS;
import tech.jiangchen.dynamicdatasource01.config.DynamicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @DS(DS.SLAVE)
//    @DS
    @Override
    public String doSth() {
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        String sql = "select * from t1";
        try (Connection conn = dynamicDataSource.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    log.info(String.valueOf(rs.getInt(1)));
                }
                rs.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return "OK";
    }
}
