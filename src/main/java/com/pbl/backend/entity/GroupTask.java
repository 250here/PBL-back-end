package com.pbl.backend.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: 杜东方
 * @date: 2020/6/2
 * @description:
 */
@Data
public class GroupTask {

    private Integer taskId;
    private Integer projectTaskId;
    private Integer groupId;
    private String taskName;
    private String taskDiscribe;
    private Timestamp taskStartTime;
    private Timestamp taskEndTime;
    private Integer isFinished;
}
