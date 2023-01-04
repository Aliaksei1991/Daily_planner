package com.example.dailyplanner.controller;

import com.example.dailyplanner.entity.Role;
import com.example.dailyplanner.entity.User;
import com.example.dailyplanner.exceptions.NoSuchUserException;
import com.example.dailyplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthorizationController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthorizationController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping(path = "/new")
    public String registView(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping(path = "/new")
    public String regist(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return "new";
        } else {
            if (userService.getUser(user.getId()) == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRole(Role.USER);
                userService.saveUser(user);
                return "redirect:/login";
            } else {
                throw new NoSuchUserException("Such a user already exists");
            }
        }
    }

    @GetMapping(path = "/login")
    public ModelAndView authorView(ModelAndView modelAndView) {
        modelAndView.addObject("userSession", new User());
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
