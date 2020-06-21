package com.pbl.backend.service.teacher.impl;

import com.alibaba.fastjson.JSONObject;
import com.pbl.backend.common.response.Result;
import com.pbl.backend.common.response.ResultCode;
import com.pbl.backend.dao.GroupPjTaskDao;
import com.pbl.backend.dao.GroupTaskDao;
import com.pbl.backend.dao.ProjectTaskDao;
import com.pbl.backend.entity.GroupPjTask;
import com.pbl.backend.entity.Project;
import com.pbl.backend.entity.ProjectTask;
import com.pbl.backend.model.PjTaskGroupRes;
import com.pbl.backend.model.ProjectTaskReq;
import com.pbl.backend.service.teacher.IProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Autowired
    private GroupPjTaskDao groupPjTaskDao;
    @Autowired
    private GroupTaskDao groupTaskDao;


    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 创建项目任务
     * @param:
     * @return: boolean
    */
    @Override
    public boolean createPjTask(ProjectTask projectTask) {
        //Timestamp startTime = Timestamp.valueOf(projectTaskReq.getTaskStartTime());
        //Timestamp endTime = Timestamp.valueOf(projectTaskReq.getTaskEndTime());
//        ProjectTask projectTask = new ProjectTask(projectTaskReq.getProjectId(), projectTaskReq.getTaskName(),
//                projectTaskReq.getTaskDiscribe(), projectTaskReq.getTaskStartTime(), projectTaskReq.getTaskEndTime());

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
    public ProjectTask getPjTask(Integer pjTaskId){
        return projectTaskDao.getPjTaskById(pjTaskId);
    }


    /**
     * @author: 杜东方
     * @date: 2020/6/14
     * @description: 获取具体PJ任务，包含每组的完成情况
     * @param:
     * @return:
    */
    @Override
    public Result getPjTaskAllGroupCompletion(Integer pjTaskId) {
        ProjectTask projectTask = projectTaskDao.getPjTaskById(pjTaskId);
        List<GroupPjTask> groupPjTasks = groupPjTaskDao.getGroupPjTasksById(pjTaskId);
        List<PjTaskGroupRes> pjTaskGroupRes = new ArrayList<>(groupPjTasks.size());
        if(projectTask == null || groupPjTasks == null || groupPjTasks.size() == 0){
            return Result.SUCCESS();
        }
        Double completion = 0.0;
        for(GroupPjTask groupPjTask : groupPjTasks){
            completion = Double.valueOf(String.format("%.2f", groupPjTask.getGroupTaskFinishNum()*1.0/groupPjTask.getGroupTaskNum()));
            pjTaskGroupRes.add(new PjTaskGroupRes(groupPjTask.getGroupId(), groupPjTask.getGroupTaskNum(),
                    groupPjTask.getGroupTaskFinishNum(), completion));
        }
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("projectTaskId", projectTask.getTaskId());
            jsonObject.put("pjTaskName", projectTask.getTaskName());
            jsonObject.put("pjTaskGroupCompletion", pjTaskGroupRes);
            return Result.SUCCESS(jsonObject);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.SYSTEM_INNER_ERROR);
        }
    }

    //删除项目任务
    @Override
    public boolean deletePjTask(int taskId) {
        //删除项目任务关联的组内任务信息
        groupTaskDao.deleteGroupTaskByProjectTaskId(taskId);
        groupPjTaskDao.deleteInfoByPjTaskId(taskId);

        projectTaskDao.deletePjTaskByTaskId(taskId);
        return true;
    }


}
