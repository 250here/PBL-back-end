package com.pbl.backend.dao;

import com.pbl.backend.entity.GroupTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupTaskDao {

    //获取小组内所有任务
    List<GroupTask> getAllGroupTasks(@Param("groupId")int groupId);

    //插入小组任务
    int addGroupTask(GroupTask groupTask);

    //根据taskId获取groupTask
    GroupTask getGroupTaskByTaskId(@Param("taskId")int taskId);

    //获取小组任务，根据groupId和taskName
    GroupTask getGroupTaskByGpIdAndName(@Param("groupId")int groupId, @Param("taskName")String taskName);

    //根据项目ID删除该项目所有小组任务
    int deleteGroupTaskByProjectId(@Param("projectId")int projectId);

    //根据groupTaskId删除
    int deleteGroupTaskByTaskId(@Param("groupTaskId")int groupTaskId);

    //更新小组任务为已完成
    int updateGruopTaskFinished(@Param("groupTaskId")int groupTaskId, @Param("isFinished")int isFinished);
}
