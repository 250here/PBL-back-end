package com.pbl.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 杜东方
 * @date: 2020/6/14
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseApplyRes {

    private String userId;
    private String userName;
    private String applyResult;
}
