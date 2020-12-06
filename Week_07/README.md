学习笔记

### 题目

按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率

#### 方法一

直接本地通过 jdbc 插入，但效率过慢。

#### 方法二

换用 jdbc batch处理，每批插入 1000 条，相比之前的方式性能有所提升，但仍然较慢，单线程执行的时候跑了半小时多才插入了将近 50W 数据。

#### 方法三

结合前面学过的多线程知识，采用 CountDownLatch 和线程池来处理，采用 10 个线程，11 分钟左右就成功插入了 100W 数据。

#### 后记

jdbcUrl 加上参数 `rewriteBatchedStatements=true`，时间缩短到26秒。。。

> 15:16:34.503 [main] INFO BatchInsertApplication - 耗时：26秒

### 使用 Sharding-jdbc 来做读写分离

#### 注意点

- spring-boot-starter 配置的数据源不能用下划线，会出错
- 需要配置读写分离的设置