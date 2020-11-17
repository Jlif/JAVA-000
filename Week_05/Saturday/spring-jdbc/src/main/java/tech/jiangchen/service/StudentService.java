package tech.jiangchen.service;

import tech.jiangchen.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    void addStudent();

    void deleteStudent();

    void updateStudent();
}
