package com.swinburne.brightboost.service.impl;

import com.swinburne.brightboost.dao.StudentCourseDao;
import com.swinburne.brightboost.domain.StudentCourse;
import com.swinburne.brightboost.service.StudentCourseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

	@Resource
	private StudentCourseDao studentCourseDao;
	
	@Override
	public List<StudentCourse> findAll(){
		return studentCourseDao.findAll();
	}

	@Override
	public List<StudentCourse> findStudentCourseByStudentId(Long studentId){
		return studentCourseDao.findStudentCourseByStudentId(studentId);
	}
	
	@Override
	public Integer save(StudentCourse tc) {
		return studentCourseDao.save(tc);
	};

	@Override
	public Integer deleteStudentCourseByStudentIdAndCourseId(Integer studentId, Integer courseId) {
		return studentCourseDao.deleteStudentCourseByStudentIdAndCourseId(studentId, courseId);
	};
}
