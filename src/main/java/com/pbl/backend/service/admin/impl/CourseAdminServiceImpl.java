package com.pbl.backend.service.admin.impl;

import com.pbl.backend.dao.CourseDao;
import com.pbl.backend.entity.Course;
import com.pbl.backend.service.admin.ICourseAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/6/13
 * @description:
 */

@Service
public class CourseAdminServiceImpl implements ICourseAdminService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = courseDao.getAllCourse();
        return courses;
    }

//    @Override
//    public Course getCourseByName(String courseName) {
//        Course course = courseDao.getCourseByCourseName(courseName);
//        return course;
//    }

    @Override
    public List<Course> getCourseByName(String courseName) {
        List<Course> courses = courseDao.getCourseByCourseName(courseName);
        return courses;
    }
}
