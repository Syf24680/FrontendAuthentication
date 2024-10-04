package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.domin.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public R login(@RequestBody User user){
        return null;
    }
}
