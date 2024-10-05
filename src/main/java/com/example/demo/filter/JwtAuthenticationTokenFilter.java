package com.example.demo.filter;

import com.alibaba.fastjson.JSON;
import com.example.demo.domin.vo.LoginUser;
import com.example.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url=request.getRequestURI();
        System.out.println(url);
        if (url.equals("/user/login")){
            filterChain.doFilter(request,response);
            return;
        }
        String token=request.getHeader("Authorization");
        if (!StringUtils.hasText(token)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置401状态
            response.getWriter().write("Token not exist");
            return; // 直接返回
        }
        // 去除可能的"Bearer "前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String loginUserStr=null;
        LoginUser loginUser=null;
        try{
            Claims claims= JwtUtils.parseJWT(token);
            loginUserStr=claims.getSubject();
            System.out.println("LoginUser JSON from JWT: " + loginUserStr);
            loginUser= JSON.parseObject(loginUserStr,LoginUser.class);
            System.out.println("Deserialized LoginUser: " + loginUser);
            System.out.println("User inside LoginUser: " + loginUser.getUser());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置401状态
            response.getWriter().write("TOKEN illegal");
            return;
        }
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginUser,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);

    }
}
