package com.swinburne.brightboost.controller;

import com.swinburne.brightboost.domain.Event;
import com.swinburne.brightboost.domain.Student;
import com.swinburne.brightboost.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    public StudentController() {
    }

    @GetMapping("/studentsLogin")
    public String getIndex(Model model) {
        model.addAttribute("student", new Student());
        return "studentLogin";
    }

    @PostMapping("/students/login")
    public String studentLogin(@ModelAttribute Student student, Model model) {
        Student studentInfo = this.studentService.studentLogin(student);
        if(studentInfo != null){
            model.addAttribute("student", studentInfo);
            return "studentInfo";
        }else{
            model.addAttribute("student", new Student());
            return "studentLogin";
        }
    }

    @GetMapping("/studentRegister")
    public String register(Model model) {
        model.addAttribute("student", new Student());
        return "studentCreate";
    }

    @GetMapping("/students/{id}")
    public String getStudentById(@PathVariable("id") String id, Model model) {
        model.addAttribute("data",this.studentService.getStudentById(Long.valueOf(id)));
        return "studentInfo";
    }

    @PostMapping("/students/save")
    public String studentSave(@ModelAttribute Student student, Model model) {
        model.addAttribute("data",this.studentService.studentSave(student));
        return "studentInfo";
    }

    @RequestMapping(
        path = {"/students/delete/{id}"},
        method = {RequestMethod.GET},
        produces = {"application/json"}
    )
    public ResponseEntity<Event> studentDelete(@PathVariable("id") String id) {
        Event event = new Event();

        try {
            this.studentService.studentDelete(Long.valueOf(id));
        } catch (Exception var4) {
            event.setResult("delete student have error." + var4);
            return ResponseEntity.ok(event);
        }

        event.setResult("result: student Successfully removed.");
        return ResponseEntity.ok(event);
    }
}
