package com.fareye.training.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({ Exception.class })
    public void handleException() {
        System.out.println("Reached exception handler");
    }
}
