package com.swinburne.brightboost.service.impl;

import com.swinburne.brightboost.dao.ClassroomDao;
import com.swinburne.brightboost.domain.Classroom;
import com.swinburne.brightboost.service.ClassroomService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

	@Resource
	private ClassroomDao classroomDao;

	@Override
	public List<Classroom> findClassroomByClassId(Long classId) {
		return classroomDao.findClassroomByClassId(classId);
	}

	@Override
	public Integer save(Classroom c) {
		return classroomDao.save(c);
	}
}
