package com.pbl.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: 杜东方
 * @date: 2020/5/17
 * @description:
 */
public class User {
    public static final String ADMIN = "admin";
    public static final String TEACHER = "teacher";
    public static final String STUDENT = "student";

    private String id;
    private String username;
    private String password;
    private String password_new;
    private Role role;
    private String photoPath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword_new() {
        return password_new;
    }

    public void setPassword_new(String password_new) {
        this.password_new = password_new;
    }
    @JsonIgnore
    public String getPhotoPath() {
        return photoPath;
    }
    @JsonProperty
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
