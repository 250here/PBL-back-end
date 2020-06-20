package com.pbl.backend.service.student;

import com.pbl.backend.entity.Course;
import com.pbl.backend.entity.CourseApply;

import java.util.List;

public interface ICourseStuService {

    boolean studentTakesCourse(String userId, int courseId);

    boolean studentDropCourse(String userId, int courseId);

    List<CourseApply> studentGetApply(String userId);

    List<Course> getJoinedCourseInfos(String userId);
}
