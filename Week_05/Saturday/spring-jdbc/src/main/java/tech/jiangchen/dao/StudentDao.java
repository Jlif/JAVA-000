package tech.jiangchen.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import tech.jiangchen.entity.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class StudentDao implements StudentRepository {

    private static String driverName = "com.mysql.jdbc.Driver";
    private static String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static String user = "root";
    private static String pwd = "123456";

    public static DataSource getDatasource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        return new HikariDataSource(config);
    }

    public static Connection getConnection() {
        Connection conn = null;
        // 加载驱动
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(jdbcUrl, user, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public List<Student> getAllStudents() {
        Connection conn = getConnection();
        String sql = "select * from student";
        try {
            List<Student> students = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setAge(rs.getInt(3));
                students.add(s);
            }
            rs.close();
            ps.close();
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void addStudent() {
        Connection conn = null;
        try {
            conn = getDatasource().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (PreparedStatement ps = conn.prepareStatement("insert into student (name,age) values (?,?)")) {
            for (int i = 0; i < 5; i++) {
                ps.setString(1, "Rose");
                ps.setInt(2, 18);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            log.error("error [{}]", e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteStudent() {
        Connection conn = getConnection();
        try (PreparedStatement ps = conn.prepareStatement("delete from student where name = 'Rose'")) {
            ps.execute();
            conn.commit();
        } catch (Exception e) {
            log.error("error [{}]", e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateStudent() {
        Connection conn = getConnection();
        try (PreparedStatement ps = conn.prepareStatement("update student set age=age+1 where id=1")) {
            ps.execute();
            conn.commit();
        } catch (Exception e) {
            log.error("error [{}]", e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
