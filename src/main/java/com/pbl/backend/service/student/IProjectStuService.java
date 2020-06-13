package com.pbl.backend.service.student;

import com.pbl.backend.entity.Project;

import java.util.List;

public interface IProjectStuService {

    List<Project> getCourseAllProjects(Integer courseId);

    Project getCourseProject(Integer projectId);

    boolean joinProject(Integer projectId, String userId);

    boolean dropProject(Integer projectId, String userId);
}
