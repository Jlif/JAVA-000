package tech.jiangchen.ssproxydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.jiangchen.ssproxydemo.service.TestService;

import javax.annotation.Resource;

@SpringBootTest
class SsProxyDemoApplicationTests {

    @Resource
    TestService testService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testInsert() {
        testService.batchInsert();
    }

}
