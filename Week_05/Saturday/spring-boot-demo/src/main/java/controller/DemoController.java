package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jiangchen.entity.Student;

import javax.annotation.Resource;

/**
 * @author jiangchen
 * @date 2020/11/18
 */
@RestController
public class DemoController {

    @Resource
    Student student;

    @RequestMapping("/test")
    public Student test() {
        return student;
    }
}
