package com.kieran.jwt.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @GetMapping("/")
    public String hello() {
        return "User access level";
    }
}
