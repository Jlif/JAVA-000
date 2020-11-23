CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名称',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系方式',
  `addr_id` int(11) DEFAULT NULL COMMENT '收货地址',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `product` (
  `product_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品名称',
  `product_category_id` int(11) unsigned NOT NULL COMMENT '产品类别id',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '产品价格',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `order` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `product_id` int(11) unsigned NOT NULL COMMENT '产品id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '购买时产品价格',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '收货地址',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;