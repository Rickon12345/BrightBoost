package com.swinburne.brightboost.service.impl;

import com.swinburne.brightboost.dao.StudentDao;
import com.swinburne.brightboost.domain.Student;
import com.swinburne.brightboost.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    public StudentServiceImpl() {
    }

    public List<Student> students() {
        return this.studentDao.findAll();
    }

    public Integer studentSave(Student student) {
        student.setStatus("Y");
        return this.studentDao.save(student);
    }

    public Student studentLogin(Student student) {
        return this.studentDao.studentLogin(student);
    }

    public Student getStudentById(Long id) {
        return this.studentDao.findById(id);
    }

    public void studentDelete(Long id) {
        this.studentDao.deleteById(id);
    }
}
