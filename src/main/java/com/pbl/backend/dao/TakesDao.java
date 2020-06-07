package com.pbl.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TakesDao {

    //学生选课
    Integer addCourse(@Param("userId")String userId, @Param("courseId")int courseId);

    //删除所选课程
    Integer deleteCourse(@Param("userId")String userId, @Param("courseId")int courseId);
}
