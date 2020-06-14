package com.pbl.backend.dao;

import com.pbl.backend.entity.CourseApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplyDao {

    //填写退课申请,status：INCHECK，AGREE，REJECT
    Integer submitApply(@Param("userId")int userId, @Param("courseId")int courseId, @Param("statusId")String statusId);

    //教师获取退课申请
    List<CourseApply> getCourseApply(@Param("courseId")int courseId);

    //学生查看自己退课申请
    List<CourseApply> studentGetCourseApply(@Param("userId")String userId);

    //更新退课申请
    Integer updateApply(@Param("userId")String userId, @Param("courseId")int courseId, @Param("code")String code);

    Integer deleteApplyInfoByCourseId(@Param("courseId") Integer courseId);
}
