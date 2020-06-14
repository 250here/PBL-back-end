package com.pbl.backend.service.teacher.impl;

import com.pbl.backend.dao.ProjectTaskDao;
import com.pbl.backend.entity.ProjectTask;
import com.pbl.backend.model.ProjectTaskReq;
import com.pbl.backend.service.teacher.IProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/5/23
 * @description: 教师项目任务管理
 */
@Service
public class ProjectTaskServiceImpl implements IProjectTaskService {

    @Autowired
    private ProjectTaskDao projectTaskDao;

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 创建项目任务
     * @param:
     * @return: boolean
    */
    @Override
    public boolean createPjTask(ProjectTaskReq projectTaskReq) {
        Timestamp startTime = Timestamp.valueOf(projectTaskReq.getTaskStartTime());
        Timestamp endTime = Timestamp.valueOf(projectTaskReq.getTaskEndTime());
        ProjectTask projectTask = new ProjectTask(projectTaskReq.getProjectId(), projectTaskReq.getTaskName(),
                projectTaskReq.getTaskDiscribe(), startTime, endTime);

        System.out.println(projectTask.getProjectId()+"==="+projectTask.getTaskName());
        ProjectTask projectTask2 = projectTaskDao.getPjTaskByPjIdAndName(projectTask.getProjectId(), projectTask.getTaskName());
        if(projectTask2 != null){ //相同项目下存在同名任务
            return false;
        }
        projectTaskDao.addProjectTask(projectTask);
        return true;
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 获取项目所有任务
     * @param: Integer projectId
     * @return: List<ProjectTask>
    */
    @Override
    public List<ProjectTask> getPjAllTasks(Integer projectId) {
        return projectTaskDao.getAllPjTasks(projectId);
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 根据任务Id获取项目任务，包含每组的完成情况
     * @param: Integer pjTaskId
     * @return:
    */
    @Override
    public ProjectTask getPjTask(Integer pjTaskId) {
        return projectTaskDao.getPjTaskById(pjTaskId);
    }

    //删除项目任务
    @Override
    public boolean deletePjTask(int taskId) {
        projectTaskDao.deletePjTaskByTaskId(taskId);
        return true;
    }
}
