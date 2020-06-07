package com.pbl.backend.dao;

import com.pbl.backend.entity.GroupPjTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GroupPjTaskDao {
    /**
     * 对应操作表——group_project_task
     */
    //根据projectTaskId和groupId
    GroupPjTask getGroupPjTaskNum(@Param("projectTaskId")int projectTaskId, @Param("groupId")int groupId);

    //更新指定项目任务，小组发布任务数/已完成数
    int updateGroupPjTaskNum(@Param("projectTaskId")int projectTaskId, @Param("groupId")int groupId, @Param("groupTaskNum")int groupTaskNum, @Param("groupTaskFinishedNum")int groupTaskFinishedNum);

    //更新指定项目任务，该小组已完成
    int updateGroupPjTaskFinished(@Param("projectTaskId")int projectTaskId, @Param("groupId")int groupId, @Param("isFinished")int isFinished);

    //创建groupTask与projectTask关联信息
    //groupTaskNum代表PjTask对应的小组创建的任务数
    int addGroupPjTask(@Param("projectTaskId")int projectTaskId, @Param("groupId")int groupId, @Param("groupTaskNum")int groupTaskNum);
}
