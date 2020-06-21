package com.pbl.backend.service.student;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.entity.Project;
import com.pbl.backend.model.StuPjEvaluation;

import java.util.List;

public interface IProjectStuService {

    List<Project> getCourseAllProjects(Integer courseId);

    List<Project> getMyCourseProject(String userId, int courseId);

    Project getCourseProject(Integer projectId);

    boolean joinProject(Integer projectId, String userId);

    boolean dropProject(Integer projectId, String userId);

    Result updateStuGrade(StuPjEvaluation stuPjEvaluation, String userId);
}
