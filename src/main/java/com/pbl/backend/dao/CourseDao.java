package com.pbl.backend.dao;

import com.pbl.backend.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseDao {

    //添加course,成功返回行数
    Integer addCourse(Course course);

    //添加进course——teacher
    //Integer updateTeaches(@Param("userId")String userId, @Param("courseId")int courseId);

    //删除course
    Integer deleteCourse(@Param("courseId")int courseId);

    //删除tea-course表
    //Integer deleteTeaCourse(@Param("userId")String userId, @Param("courseId")int courseId);

    //查找course
    Course getCourse(@Param("teacherId")String teacherId, @Param("courseName")String courseName);

    //根据id或者名称查找
    Course getCourseByCourseId(@Param("courseId")int courseId);
    Course getCourseByCourseName(@Param("courseName")String courseName);

    //删除学生所选课程
    Integer deleteStudentTake(@Param("courseId")int courseId);

    //获取老师所授课程
    List<Course> getCourseByTeacherId(@Param("userId")String userId);

    List<Course> getAllCourse();


}
