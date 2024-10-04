package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.domin.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public R login(@RequestBody User user){
        String jwt=userService.login(user);
        if (StringUtils.hasLength(jwt)){
            return R.ok().message("登陆成功").data("token",jwt);
        }
        return R.error().message("登录失败");
    }
}
