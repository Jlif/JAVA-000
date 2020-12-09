学习笔记

### 必做题一

> 设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github

- 先从官网下载 `apache-shardingsphere-5.0.0-alpha-shardingsphere-proxy-bin`
- 修改配置文件，启动中间件，默认启动于 `3307` 端口
- 通过容器启动两个数据库，shard1和shard2，分别启动于3326和3327端口
- 在两个数据库中新建test0跟test1数据库
- 使用mysql客户端连接ss-proxy，执行 create table 命令，ss-proxy自动于两个库中按照规则新建分表