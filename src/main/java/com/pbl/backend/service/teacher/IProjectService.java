package com.pbl.backend.service.teacher;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.entity.Project;

import java.util.List;

public interface IProjectService {

    boolean createProject(Project project);

    List<Project> getCourseAllProjects(int courseId);

    Project getCourseProject(int projectId);

    boolean deleteProject(int projectId);

    Result getGradeRefData(int projectId);

    boolean updateStuGrade(int projectId, String stuId, int grade);

}
