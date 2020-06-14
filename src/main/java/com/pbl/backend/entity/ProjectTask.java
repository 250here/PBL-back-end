package com.pbl.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName ProjectTask
 * @Description
 * @Author 孟超
 * @Date 2020/6/1
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTask {

    private Integer taskId;
    private Integer projectId;
    private String taskName;
    private String taskDiscribe;
    private Timestamp taskStartTime;
    private Timestamp taskEndTime;

    public ProjectTask(Integer projectId, String taskName, String taskDiscribe,
                       Timestamp taskStartTime, Timestamp taskEndTime){
        this.projectId = projectId;
        this.taskName = taskName;
        this.taskDiscribe = taskDiscribe;
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }

}
