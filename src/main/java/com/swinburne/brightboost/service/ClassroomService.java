package com.swinburne.brightboost.service;

import com.swinburne.brightboost.domain.Classroom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassroomService {

	public List<Classroom> findClassroomByClassId(Long classId);

	public Integer save(Classroom c);
}
