package com.pbl.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: 杜东方
 * @date: 2020/6/14
 * @description:  项目任务每个小组的完成情况
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PjTaskGroupRes implements Serializable {

    private Integer groupId;
    private Integer groupTaskNum;
    private Integer groupTaskFinishNum;
    private Double taskCompletion;
}
