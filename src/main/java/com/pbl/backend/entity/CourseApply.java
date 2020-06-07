package com.pbl.backend.entity;

import lombok.Data;

/**
 * @ClassName CourseApply
 * @Description
 * @Author 孟超
 * @Date 2020/6/1
 **/

@Data
public class CourseApply {
    private Integer courseId;
    private String courseName;
    private String applyResult;
    private String userId;
    private String userName;

    //维护学生和apply之间的一对一关系
    private User user;

    private Course course;

    public static final String INCHECK = "0";
    public static final String AGREE = "1";
    public static final String REJECT = "2";

}
