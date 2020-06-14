package com.pbl.backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 杜东方
 * @date: 2020/6/13
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTaskReq {

    private Integer taskId;
    private Integer projectId;
    private String taskName;
    private String taskDiscribe;
    private String taskStartTime;
    private String taskEndTime;
}
