package com.example.dailyplanner.controller;

import com.example.dailyplanner.entity.User;
import com.example.dailyplanner.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/myLogout")
public class LogoutController {
    private final SessionService sessionService;

    public LogoutController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String logout(HttpSession session, ModelAndView modelAndView) {
        User user = sessionService.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
