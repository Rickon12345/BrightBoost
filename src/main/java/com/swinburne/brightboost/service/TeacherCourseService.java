package com.swinburne.brightboost.service;

import java.util.List;

import com.swinburne.brightboost.domain.TeacherCourse;
import org.springframework.stereotype.Service;

@Service
public interface TeacherCourseService {
	public List<TeacherCourse> findAll();

	public List<TeacherCourse> findTeacherCourseByTeacherId(Long teacherId);

	public Integer save(TeacherCourse tc);

	public Integer deleteTeacherCourseByTeacherIdAndCourseId(Integer teacherId, Integer courseId);
}
