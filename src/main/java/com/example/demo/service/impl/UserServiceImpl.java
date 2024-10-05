package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domin.User;
import com.example.demo.domin.vo.LoginUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public String login(User user) {
        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser =(LoginUser) authenticate.getPrincipal();
        String loginUserStr = JSON.toJSONString(loginUser);
        System.out.println("Serialized LoginUser: " + loginUserStr);
        String jwt = JwtUtils.createJWT(loginUserStr, null);
        return jwt;
    }
}
