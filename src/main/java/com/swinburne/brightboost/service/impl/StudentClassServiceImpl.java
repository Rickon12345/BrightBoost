package com.swinburne.brightboost.service.impl;

import com.swinburne.brightboost.dao.StudentClassDao;
import com.swinburne.brightboost.domain.StudentClass;
import com.swinburne.brightboost.domain.StudentClass;
import com.swinburne.brightboost.service.StudentClassService;
import com.swinburne.brightboost.service.StudentClassService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClassServiceImpl implements StudentClassService {

	@Resource
	private StudentClassDao studentClassDao;
	
	@Override
	public List<StudentClass> findAll(){
		return studentClassDao.findAll();
	}

	@Override
	public List<StudentClass> findStudentClassByStudentId(Long studentId){
		return studentClassDao.findStudentClassByStudentId(studentId);
	}

	@Override
	public Integer deleteStudentClassByStudentIdAndClassId(Integer studentId, Integer classId) {
		return studentClassDao.deleteStudentClassByStudentIdAndClassId(studentId, classId);
	};

	@Override
	public Integer save(StudentClass sc) {
		return studentClassDao.save(sc);
	}

}
