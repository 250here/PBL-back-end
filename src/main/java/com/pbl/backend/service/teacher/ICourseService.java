package com.pbl.backend.service.teacher;

import com.pbl.backend.entity.Course;
import com.pbl.backend.entity.CourseApply;
import com.pbl.backend.model.CourseApplyRes;

import java.util.List;

public interface ICourseService {

    boolean createCourse(Course course);

    boolean deleteCourse(int courseId,String userId);

    List<Course> getCoursesOfTeacher(String teacherId);

    Course getCourseByCourseId(int courseId);

    List<CourseApplyRes> getAllCourseApply(int courseId);

    boolean handleApply(String userId, int courseId ,String code);
}
