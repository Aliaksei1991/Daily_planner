package com.example.dailyplanner.service;

import com.example.dailyplanner.entity.User;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final UserService userService;

    @Autowired
    public SessionService(UserService userService) {
        this.userService = userService;
    }

    public User getSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        return userService.findUserByUsername(username);
    }
}
