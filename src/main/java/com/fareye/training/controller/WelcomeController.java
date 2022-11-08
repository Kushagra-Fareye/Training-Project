package com.fareye.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WelcomeController {
    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "hello welcome to spring";
    }

    @RequestMapping(value = "/api/username", method = RequestMethod.GET)
    @ResponseBody
    public String currUser(Authentication auth) {
        return "hello welcome to spring" + auth.getName();
    }

    @RequestMapping(value = "/api/admin/user/{username}", method = RequestMethod.GET)
    public UserDetails getListOfUser(@PathVariable String username) {
        return userDetailsService.loadUserByUsername(username);
    }

}