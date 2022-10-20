package com.fareye.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fareye.training.service.MyService;

@RestController
public class BeanController {
    @Autowired
    private MyService myService;

    @RequestMapping("/test-beans")
    public String testBeans() {
        myService.printItem();
        return "done";
    }

}
