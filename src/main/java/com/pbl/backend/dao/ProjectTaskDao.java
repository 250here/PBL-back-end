package com.pbl.backend.dao;

import com.pbl.backend.entity.Project;
import com.pbl.backend.entity.ProjectTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectTaskDao {

    //添加pjTask
    Integer addProjectTask(ProjectTask projectTask);

    //根据pjId和pjTaskName查找项目
    ProjectTask getPjTaskByPjIdAndName(@Param("projectId")int projectId, @Param("taskName")String taskName);

    //获取项目所有任务
    List<ProjectTask> getAllPjTasks(@Param("projectId")int projectId);

    //根据taskId查找PjTask
    ProjectTask getPjTaskById(@Param("taskId")int taskId);

    //根据taskId删除项目任务信息
    int deletePjTaskByTaskId(@Param("pjTaskId")int pjTaskId);

    //根据项目ID删除该项目所有任务信息
    int deletePjTasksByProjectId(@Param("projectId")int projectId);

    //获取项目发布任务数
    int getPjTaskNum(int projectId);
}
