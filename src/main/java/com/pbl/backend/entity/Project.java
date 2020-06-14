package com.pbl.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @ClassName Project
 * @Description
 * @Author 孟超
 * @Date 2020/6/1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private Integer projectId;
    private Integer courseId;
    private String projectName;
    private Date startTime;
    private Date endTime;
    private String projectDsc;
    private Date evaluationTime;
    private Integer teacherEvaWeight;  //教师评价权重
    private Integer stuEvaWeight;   //学生评价权重, 和教师总和为100%

    @JsonIgnore
    public Integer getCourseId() {
        return courseId;
    }

    @JsonProperty
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
