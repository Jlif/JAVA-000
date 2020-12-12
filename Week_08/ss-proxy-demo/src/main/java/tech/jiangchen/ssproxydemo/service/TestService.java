package tech.jiangchen.ssproxydemo.service;

import tech.jiangchen.ssproxydemo.entity.Order;

public interface TestService {
    void batchInsert();

    Order select(Long orderId);
}
