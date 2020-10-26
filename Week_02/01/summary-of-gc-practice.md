### 串行/并行/G1 案例演练

#### 串行

> java -XX:+UseSerialGC -Xloggc:gc.log -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

> java -XX:+UseSerialGC -Xloggc:gc.log -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

> java -XX:+UseSerialGC -Xloggc:gc.log -Xms4g -Xmx4g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

#### 并行

> java -XX:+UseParallelGC -Xloggc:gc.log -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

> java -XX:+UseParallelGC -Xloggc:gc.log -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

> java -XX:+UseParallelGC -Xloggc:gc.log -Xms4g -Xmx4g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

#### CMS

> java -XX:+UseConcMarkSweepGC -Xloggc:gc.log -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

> java -XX:+UseConcMarkSweepGC -Xloggc:gc.log -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

> java -XX:+UseConcMarkSweepGC -Xloggc:gc.log -Xms4g -Xmx4g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

#### G1

> java -XX:+UseG1GC -Xloggc:gc.log -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

> java -XX:+UseG1GC -Xloggc:gc.log -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

> java -XX:+UseG1GC -Xloggc:gc.log -Xms4g -Xmx4g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

#### 汇总表格

| Serial GC | Parallel GC | CMS GC | G1 GC |
| :--- | :--- | :--- | :--- | :--- |
| 128M | OOM（26 次 GC，20 次 Full GC） | OOM（27 次 GC，17 次 Full GC） |  OOM（24 次 GC，13 次 Full GC） | OOM（34 次 GC，11 次 Full GC） |
| 512M | 6837（13 次 GC，4 次 Full GC） | 6933（28 次 GC，4 次 Full GC） |  9023（29 次 GC，12 次 Full GC）  | 9232（47 次 GC，21 次 Full GC） |
| 4G | 8505（2 次 GC,0 次 Full GC） | 10228（2 次 GC,0 次 Full GC） |  11363（11 次 GC，0 次 Full GC）  | 11809（14 次 GC，0 次 Full GC） |

#### 总结

总体来看，当堆内存越大，GC 越不频繁，性能越好。当然，因为示例代码存在内存泄露的问题，只要时间够久，也总会 OOM 的。

至于并行 GC 在小内存的时候并没有表现出我想象中的性能优势，我的猜想是因为内存泄漏，每次 GC 其实都占用了不少时间，但其实越往后，能够回收的内存越少。因为 CMS 和 G1 对暂停时间有一定的控制，除非回收次数显著高于并行 GC，不然在示例代码中并行 GC 的优势并不明显。

在生产中，一般堆比较小的时候还是采用并行 GC 回收器，大内存的时候采用 G1 回收器，如果对延迟有特殊的要求，可以考虑 ZGC。