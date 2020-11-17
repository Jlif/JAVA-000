package tech.jiangchen.service;

import org.springframework.stereotype.Service;
import tech.jiangchen.dao.StudentRepository;
import tech.jiangchen.entity.Student;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public void addStudent() {
        studentRepository.addStudent();
    }

    @Override
    public void deleteStudent() {
        studentRepository.deleteStudent();
    }

    @Override
    public void updateStudent() {
        studentRepository.updateStudent();
    }
}
