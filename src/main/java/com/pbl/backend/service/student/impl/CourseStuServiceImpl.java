package com.pbl.backend.service.student.impl;

import com.pbl.backend.dao.ApplyDao;
import com.pbl.backend.dao.TakesDao;
import com.pbl.backend.service.student.ICourseStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
