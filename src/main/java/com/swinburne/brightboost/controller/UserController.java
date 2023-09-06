package com.swinburne.brightboost.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.swinburne.brightboost.domain.Event;
import com.swinburne.brightboost.domain.User;
import com.swinburne.brightboost.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/users/login")
    public String userLogin(@ModelAttribute User user, Model model) {
        User userInfo = this.userService.userLogin(user);
        if(userInfo != null){
            model.addAttribute("user", userInfo);
            return "userInfo";
        }else{
            model.addAttribute("user", new User());
            return "index";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "userCreate";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users",this.userService.users());
        return "userList";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable("id") String id, Model model) {
        model.addAttribute("data",this.userService.getUserById(Long.valueOf(id)));
        return "userInfo";
    }

    @PostMapping("/users/save")
    public String userSave(@ModelAttribute User user, Model model) {
        model.addAttribute("data",this.userService.userSave(user));
        return "users";
    }

    @RequestMapping(
        path = {"/users/delete/{id}"},
        method = {RequestMethod.GET},
        produces = {"application/json"}
    )
    public ResponseEntity<Event> userDelete(@PathVariable("id") String id) {
        Event event = new Event();

        try {
            this.userService.userDelete(Long.valueOf(id));
        } catch (Exception var4) {
            event.setResult("delete user have error." + var4);
            return ResponseEntity.ok(event);
        }

        event.setResult("result: user Successfully removed.");
        return ResponseEntity.ok(event);
    }
}
