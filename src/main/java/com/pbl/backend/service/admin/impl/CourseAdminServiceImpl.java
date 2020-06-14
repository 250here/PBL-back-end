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
        return null;
    }

    @Override
    public Course getCourseByName(String courseName) {
        return null;
    }
}
