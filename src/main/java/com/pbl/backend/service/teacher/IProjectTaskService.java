package com.pbl.backend.service.teacher;

import com.pbl.backend.entity.ProjectTask;
import com.pbl.backend.model.ProjectTaskReq;

import java.util.List;

public interface IProjectTaskService {

    boolean createPjTask(ProjectTaskReq projectTaskReq);

    List<ProjectTask> getPjAllTasks(Integer projectId);

    ProjectTask getPjTask(Integer pjTaskId);

    boolean deletePjTask(int taskId);

}
