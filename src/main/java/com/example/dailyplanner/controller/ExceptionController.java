package com.example.dailyplanner.controller;

import com.example.dailyplanner.exceptions.NoAuthoriseUserFoundException;
import com.example.dailyplanner.exceptions.NoSuchProductException;
import com.example.dailyplanner.exceptions.NoSuchUserException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoSuchUserException.class)
    public String noUserException(NoSuchUserException e, Model model){
        model.addAttribute("errorMessage",e.getMessage());
        return "errorPage";
    }

    @ExceptionHandler(NoAuthoriseUserFoundException.class)
    public String noAuthoriseUserException(NoAuthoriseUserFoundException e, Model model) {
        model.addAttribute("errorMessage",e.getMessage());
        return "errorPage";
    }

    @ExceptionHandler(NoSuchProductException.class)
    public String noProductException(NoSuchProductException e, Model model) {
        model.addAttribute("errorMessage",e.getMessage());
        return "errorPage";
    }
}
