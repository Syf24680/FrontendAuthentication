package com.example.demo.domin.vo;

import com.example.demo.domin.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class LoginUser implements UserDetails {
    private User user;
    //用来返回权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    //获取密码
    @Override
    public String getPassword() {
        return user.getPassword();
    }
//获取用户名
    @Override
    public String getUsername() {
        return user.getUserName();
    }
    //判断账号是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //判断账号是否没有锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //判断账号是否没有超时
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //判断账号是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }

}
