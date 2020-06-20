package com.pbl.backend.service.admin;

import com.pbl.backend.entity.Course;

import java.util.List;

public interface ICourseAdminService {


    List<Course> getAllCourses();
//    Course getCourseByName(String courseName);
    List<Course> getCourseByName(String courseName);
}
