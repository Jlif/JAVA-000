package tech.jiangchen.shardingjdbcdemo.service;

import tech.jiangchen.shardingjdbcdemo.entity.Order;

public interface OrderService {
    /**
     * 插入订单数据
     */
    int insert(final Order order);

    /**
     * 查询订单
     */
    Order selectById(int id);
}
