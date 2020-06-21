package com.pbl.backend.service.student.impl;

import com.pbl.backend.dao.ApplyDao;
import com.pbl.backend.dao.CourseDao;
import com.pbl.backend.dao.TakesDao;
import com.pbl.backend.entity.Course;
import com.pbl.backend.entity.CourseApply;
import com.pbl.backend.service.student.ICourseStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CourseStuServiceImpl
 * @Description
 * @Author 孟超
 * @Date 2020/6/14
 **/
@Service
public class CourseStuServiceImpl implements ICourseStuService {

    @Autowired
    TakesDao takesDao;

    @Autowired
    ApplyDao applyDao;

    @Autowired
    CourseDao courseDao;

    @Override
    public boolean studentTakesCourse(String userId, int courseId) {
        takesDao.addCourse(userId, courseId);
        return true;
    }

    @Override
    public boolean studentDropCourse(String userId, int courseId) {
        //takesDao.deleteCourse(userId, courseId);
        applyDao.submitApply(userId,courseId,"0");
        return true;
    }

    @Override
    public List<CourseApply> studentGetApply(String userId) {

        return applyDao.studentGetCourseApply(userId);
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/20
     * @description: 获取已经加入的课程
     * @param:
     * @return:
    */
    @Override
    public List<Course> getJoinedCourseInfos(String userId) {
        return courseDao.getJoinedCourses(userId);
    }
}
