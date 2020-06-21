package com.pbl.backend.model;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/6/21
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuPjEvaluation {

    private Integer projectId;
    private Integer groupId;
    private List<StuEvaluate> stuEvaluates;
}
