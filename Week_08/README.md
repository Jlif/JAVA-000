学习笔记

### 必做题一

> 设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github

- 先从官网下载 `apache-shardingsphere-5.0.0-alpha-shardingsphere-proxy-bin`
- 修改配置文件，启动中间件，默认启动于 `3307` 端口
- 通过容器启动两个数据库，shard1和shard2，分别启动于3326和3327端口
- 在两个数据库中新建test0跟test1数据库
- 使用mysql客户端连接ss-proxy，执行 create table 命令，ss-proxy自动于两个库中按照规则新建分表

```mysql
CREATE TABLE `t_order` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `product_id` int(11) unsigned NOT NULL COMMENT '产品id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '购买时产品价格',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '收货地址',
  `is_delete` tinyint(1) unsigned DEFAULT NULL COMMENT '删除标记',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

```mysql
CREATE TABLE `t_order` (
  `order_id` varchar(32) NOT NULL COMMENT '订单id',
  `product_id` int(11) unsigned NOT NULL COMMENT '产品id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '购买时产品价格',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '收货地址',
  `is_delete` tinyint(1) unsigned DEFAULT NULL COMMENT '删除标记',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

命令行中好像不能正确处理utf-8，注释乱码