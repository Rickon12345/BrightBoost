package com.swinburne.brightboost.service;

import com.swinburne.brightboost.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    List<Teacher> teachers();

    Integer teacherSave(Teacher var1);

    Teacher teacherLogin(Teacher var1);

    void teacherDelete(Long var1);

    Teacher getTeacherById(Long var1);
}
