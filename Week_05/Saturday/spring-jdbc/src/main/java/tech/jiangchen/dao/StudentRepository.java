package tech.jiangchen.dao;

import tech.jiangchen.entity.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudents();

    void addStudent();

    void deleteStudent();

    void updateStudent();
}
