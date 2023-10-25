package com.swinburne.brightboost.service.impl;

import java.util.List;


import com.swinburne.brightboost.dao.TeacherCourseDao;
import com.swinburne.brightboost.domain.TeacherCourse;
import com.swinburne.brightboost.service.TeacherCourseService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

	@Resource
	private TeacherCourseDao teacherCourseDao;
	
	@Override
	public List<TeacherCourse> findAll(){
		return teacherCourseDao.findAll();
	}

	@Override
	public List<TeacherCourse> findTeacherCourseByTeacherId(Long teacherId){
		return teacherCourseDao.findTeacherCourseByTeacherId(teacherId);
	}

	@Override
	public List<TeacherCourse> findTeacherCourseByCourseId(Long courseId){
		return teacherCourseDao.findTeacherCourseByCourseId(courseId);
	}

	@Override
	public Integer save(TeacherCourse tc) {
		return teacherCourseDao.save(tc);
	};

	@Override
	public Integer deleteTeacherCourseByTeacherIdAndCourseId(Integer teacherId, Integer courseId) {
		return teacherCourseDao.deleteTeacherCourseByTeacherIdAndCourseId(teacherId, courseId);
	};
}
