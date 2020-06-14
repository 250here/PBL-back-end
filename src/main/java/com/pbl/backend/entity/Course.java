package com.pbl.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @ClassName Course
 * @Description
 * @Author 孟超
 * @Date 2020/6/1
 **/

@Data
public class Course {
    private Integer courseId;
    private String teacherId;
    private String courseName;
    private Date courseStartTime;
    private Date courseEndTime;

    private Integer stuNum; //课程学生数

    @JsonIgnore
    public String getTeacherId() {
        return teacherId;
    }

    @JsonProperty
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

}
