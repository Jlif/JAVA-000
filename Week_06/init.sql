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

CREATE TABLE `user_addr` (
  `user_addr_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `addr` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '收货地址',
  `province_code` varchar(12) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '省地址码',
  `city_code` varchar(12) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '市地址码',
  `area_code` varchar(12) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '区地址码',
  PRIMARY KEY (`user_addr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `user_wallet` (
  `wallet_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '钱包id',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户id',
  `balance` bigint(20) DEFAULT NULL COMMENT '余额',
  `create_by` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`wallet_id`)
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

CREATE TABLE `product_stock` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '库存id',
  `product_id` int(11) unsigned DEFAULT NULL COMMENT '商品id',
  `stock` int(11) unsigned DEFAULT NULL COMMENT '库存量',
  `create_by` int(11) unsigned DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` int(11) unsigned DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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