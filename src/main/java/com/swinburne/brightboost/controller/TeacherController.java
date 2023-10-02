package com.swinburne.brightboost.controller;

import com.swinburne.brightboost.domain.Event;
import com.swinburne.brightboost.domain.Teacher;
import com.swinburne.brightboost.domain.TeacherCourse;
import com.swinburne.brightboost.service.CourseService;
import com.swinburne.brightboost.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    HttpServletRequest request;

    public TeacherController() {
    }

    @GetMapping("/teachers/login")
    public String getIndex(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacherLogin";
    }

    @PostMapping("/teachers/login")
    public String teacherLogin(HttpServletRequest request, @ModelAttribute Teacher teacher, Model model) {
        Teacher teacherInfo = this.teacherService.teacherLogin(teacher);
        if(teacherInfo != null){
            HttpSession session = request.getSession();
            session.setAttribute("teacherId",teacherInfo.getId());
            model.addAttribute("teacher", teacherInfo);
            return "teacherInfo";
        }else{
            model.addAttribute("teacher", new Teacher());
            return "index";
        }
    }

    @GetMapping("/teacher/register")
    public String register(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacherCreate";
    }

    @GetMapping("/teacher/course")
    public String chooseCourse(Model model) {
        Long id = (Long) request.getSession().getAttribute("teacherId");
        model.addAttribute("teacher", this.teacherService.getTeacherById(id));
        model.addAttribute("courses", courseService.courses());
        model.addAttribute("teacherCourse",new TeacherCourse());
        return "teacherCourseCreate";
    }

    @GetMapping("/teachers/{id}")
    public String getTeacherById(@PathVariable("id") String id, Model model) {
        model.addAttribute("data",this.teacherService.getTeacherById(Long.valueOf(id)));
        return "teacherInfo";
    }

    @PostMapping("/teachers/save")
    public String teacherSave(@ModelAttribute Teacher teacher, Model model) {
        model.addAttribute("data",this.teacherService.teacherSave(teacher));
        return "teacherInfo";
    }

    @RequestMapping(
        path = {"/teachers/delete/{id}"},
        method = {RequestMethod.GET},
        produces = {"application/json"}
    )
    public ResponseEntity<Event> teacherDelete(@PathVariable("id") String id) {
        Event event = new Event();

        try {
            this.teacherService.teacherDelete(Long.valueOf(id));
        } catch (Exception var4) {
            event.setResult("delete teacher have error." + var4);
            return ResponseEntity.ok(event);
        }

        event.setResult("result: teacher Successfully removed.");
        return ResponseEntity.ok(event);
    }
}
