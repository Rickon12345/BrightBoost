package com.swinburne.brightboost.service;

import java.util.List;

import com.swinburne.brightboost.domain.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
//	public Course findByName(String name);
	public List<Course> courses();
	public Integer courseSave(Course course);
	public void courseDelete(Long id);
	public Course getCourseById(Long id);
}
