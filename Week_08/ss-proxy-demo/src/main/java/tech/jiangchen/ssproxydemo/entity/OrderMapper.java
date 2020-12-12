package tech.jiangchen.ssproxydemo.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getLong(1));
        order.setProductId(resultSet.getInt(2));
        order.setUserId(resultSet.getInt(3));
        order.setProductPrice(resultSet.getBigDecimal(4));
        order.setAddress(resultSet.getString(5));
        order.setIsDelete(resultSet.getInt(6));
        order.setCreateTime(resultSet.getDate(7));
        order.setCreateBy(resultSet.getInt(8));
        order.setUpdateTime(resultSet.getDate(9));
        order.setUpdateBy(resultSet.getInt(10));
        return order;
    }
}
