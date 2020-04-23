CREATE TABLE `book` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `book_name` varchar(50) DEFAULT NULL,
  `book_author` varchar(100) NOT NULL DEFAULT '1',
  `book_price` decimal(10,0) DEFAULT NULL,
  `push_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`book_id`) USING BTREE,
  KEY `idx_business_no` (`book_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;