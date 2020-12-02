package tech.jiangchen.shardingjdbcdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jiangchen.shardingjdbcdemo.service.TestService;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    TestService testService;

    @RequestMapping("/")
    public String test() {
        return testService.doSth();
    }
}
