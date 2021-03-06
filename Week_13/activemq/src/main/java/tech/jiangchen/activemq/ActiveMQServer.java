package tech.jiangchen.activemq;

import org.apache.activemq.broker.BrokerService;

public class ActiveMQServer {

    public static void main(String[] args) throws Exception {
        // 尝试用java代码启动一个ActiveMQ broker server
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.setBrokerName("myBroker");
        brokerService.setPersistent(Boolean.TRUE);
        brokerService.addConnector("tcp://127.0.0.1:61616");
        brokerService.start();
        // 然后用前面的测试demo代码，连接这个嵌入式的server
    }
}
