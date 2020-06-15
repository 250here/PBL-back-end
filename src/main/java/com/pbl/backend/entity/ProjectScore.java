package com.pbl.backend.entity;

import lombok.Data;

/**
 * @ClassName ProjectScoreDao
 * @Description
 * @Author 孟超
 * @Date 2020/6/1
 **/

@Data
public class ProjectScore {

    private Integer projectId;
    private String projectName;
    private String userId;
    private String userName;

    private Integer stuGrade;
    private Integer teacherGrade;

    private Integer participation;  //学生参与度

    private Integer groupId; //学生所在小组Id

    private String pjTaskCompletion; //所在小组项目任务完成度
}
