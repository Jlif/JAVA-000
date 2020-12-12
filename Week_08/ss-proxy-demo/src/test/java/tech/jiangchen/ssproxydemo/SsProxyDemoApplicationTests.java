package tech.jiangchen.ssproxydemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.jiangchen.ssproxydemo.service.TestService;

import javax.annotation.Resource;

@Slf4j
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

    @Test
    public void testSelect() {
        log.info(testService.select(544542387692089355L).toString());
    }

}
