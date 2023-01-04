package com.example.dailyplanner.controller;

import com.example.dailyplanner.entity.User;
import com.example.dailyplanner.service.SessionService;
import com.example.dailyplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    private UserService userService;
    private SessionService sessionService;

    @Autowired
    public MainController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("/")
    public String home(Model model, @ModelAttribute User user) {
        model.addAttribute("user", user);
        return "home";
    }
}
