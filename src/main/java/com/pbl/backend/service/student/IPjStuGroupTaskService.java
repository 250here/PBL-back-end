package com.pbl.backend.service.student;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.entity.GroupTask;

import java.util.List;

public interface IPjStuGroupTaskService {

    Result createPjGroupTask(GroupTask groupTask);

    List<GroupTask> getPjGroupAllTasks(Integer groupId);

    GroupTask getPjGroupTask();

    boolean deletePjGroupTask(int pjTaskId, int groupId, int groupTaskId, boolean isFinished);

    boolean updateGroupTask(int pjTaskId, int groupId, int groupTaskId);
}
