package com.pbl.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 杜东方
 * @date: 2020/6/21
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuEvaluate {

    private String userId;
    private int grade;
}
