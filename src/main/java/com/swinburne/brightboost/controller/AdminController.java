package com.swinburne.brightboost.controller;

import com.swinburne.brightboost.domain.Event;
import com.swinburne.brightboost.domain.User;
import com.swinburne.brightboost.service.CourseService;
import com.swinburne.brightboost.service.StudentService;
import com.swinburne.brightboost.service.TeacherService;
import com.swinburne.brightboost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    public AdminController() {
    }

    @GetMapping("/teachers")
    public String teachers(Model model) {
        model.addAttribute("teachers",this.teacherService.teachers());
        return "teacherList";
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students",this.studentService.students());
        return "studentList";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses",this.courseService.courses());
        return "courseList";
    }
}
