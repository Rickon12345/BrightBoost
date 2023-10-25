package com.swinburne.brightboost.service;

import com.swinburne.brightboost.domain.StudentClass;
import com.swinburne.brightboost.domain.TeacherCourse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentClassService {

	public List<StudentClass> findAll();

	public List<StudentClass> findStudentClassByStudentId(Long studentId);

	public Integer save(StudentClass sc);

	public Integer deleteStudentClassByStudentIdAndClassId(Integer studentId, Integer classId);
}
