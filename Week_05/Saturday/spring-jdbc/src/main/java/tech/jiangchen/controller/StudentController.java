package tech.jiangchen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jiangchen.entity.Student;
import tech.jiangchen.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StudentController {
    @Resource
    StudentService studentService;

    @GetMapping("/sel")
    public List<Student> test() {
        return studentService.getAllStudents();
    }

    @GetMapping("/add")
    public void add() {
        studentService.addStudent();
    }

    @GetMapping("/del")
    public void del() {
        studentService.deleteStudent();
    }

    @GetMapping("/update")
    public void update() {
        studentService.updateStudent();
    }
}
