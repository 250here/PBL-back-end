package com.pbl.backend.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author: 杜东方
 * @date: 2020/5/17
 * @description: 需要实现UserDetails接口，用于SpringSecurity的用户状态认证
 * （登录用户名密码、用户是否锁定、用户账号是否可用.....）
 */
public class JwtUser implements UserDetails {

    private String id;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean locked;

    //用户具备的角色
    private Collection<? extends GrantedAuthority> authorities;

    //登录后返回token
    private String token;

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtUser(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName()));
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账户是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    //获取用户角色，即权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // 我自己重写打印下信息看的
    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
