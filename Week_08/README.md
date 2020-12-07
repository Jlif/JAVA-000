学习笔记

### 数据库迁移

#### 下载安装运行中间件

- 先从官网下载 `apache-shardingsphere-5.0.0-alpha-shardingsphere-scaling-bin`
- 使用默认配置，启动中间件，启动于 `8888` 端口
- 使用容器启动三个数据库，源库开启binlog

> create user 'shard'@'%' identified by '123456';
> GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'shard'@'%';
> flush privileges;

- 源库新建test数据库，插入一百万条订单数据

```json
{
        "ruleConfiguration": {
          "source": {
            "type": "shardingSphereJdbc",
            "parameter": {
              "dataSource":"
                dataSources:
                  ds_0:
                    dataSourceClassName: com.zaxxer.hikari.HikariDataSource
                    props:
                      driverClassName: com.mysql.jdbc.Driver
                      jdbcUrl: jdbc:mysql://127.0.0.1:3326/scaling_0?useSSL=false
                      username: root
                      password:
                  ds_1:
                    dataSourceClassName: com.zaxxer.hikari.HikariDataSource
                    props:
                      driverClassName: com.mysql.jdbc.Driver
                      jdbcUrl: jdbc:mysql://127.0.0.1:3327/scaling_1?useSSL=false
                      username: root
                      password:
                ",
              "rule":"
                rules:
                - !SHARDING
                  tables:
                    t_order:
                      actualDataNodes: ds_$->{0..1}.t_order_$->{0..7}
                      databaseStrategy:
                        standard:
                          shardingColumn: order_id
                          shardingAlgorithmName: t_order_db_algorith
                      logicTable: t_order
                      tableStrategy:
                        standard:
                          shardingColumn: user_id
                          shardingAlgorithmName: t_order_tbl_algorith
                  shardingAlgorithms:
                    t_order_db_algorith:
                      type: INLINE
                      props:
                        algorithm-expression: ds_$->{order_id % 2}
                    t_order_tbl_algorith:
                      type: INLINE
                      props:
                        algorithm-expression: t_order_$->{user_id % 2}
                "
            }
          },
          "target": {
              "type": "jdbc",
              "parameter": {
                "username": "root",
                "password": "",
                "jdbcUrl": "jdbc:mysql://127.0.0.1:3316/test?serverTimezone=UTC&useSSL=false"
              }
          }
        },
        "jobConfiguration":{
          "concurrency":"3"
        }
      }
```