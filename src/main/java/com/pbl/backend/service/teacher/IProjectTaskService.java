package com.pbl.backend.service.teacher;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.entity.ProjectTask;
import com.pbl.backend.model.PjTaskGroupRes;
import com.pbl.backend.model.ProjectTaskReq;

import java.util.List;

public interface IProjectTaskService {

    boolean createPjTask(ProjectTask projectTask);

    List<ProjectTask> getPjAllTasks(Integer projectId);

    ProjectTask getPjTask(Integer pjTaskId);

    Result getPjTaskAllGroupCompletion(Integer pjTaskId);

    boolean deletePjTask(int taskId);

}
