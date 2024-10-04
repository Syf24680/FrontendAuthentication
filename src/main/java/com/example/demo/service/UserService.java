package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domin.User;

public interface UserService extends IService<User> {
    String login(User user);
}
