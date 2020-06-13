package com.pbl.backend.service.student.impl;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.common.response.ResultCode;
import com.pbl.backend.dao.GroupPjTaskDao;
import com.pbl.backend.dao.GroupTaskDao;
import com.pbl.backend.entity.GroupPjTask;
import com.pbl.backend.entity.GroupTask;
import com.pbl.backend.service.student.IPjStuGroupTaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/6/1
 * @description:
 */
public class PjStuGroupTaskServiceImpl implements IPjStuGroupTaskService {

    @Autowired
    private GroupTaskDao groupTaskDao;

    @Autowired
    private GroupPjTaskDao groupPjTaskDao;
    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description:
     * @param:
     * @return:
    */
    @Override
    public Result createPjGroupTask(GroupTask groupTask) {
        //查看此人是否是组长

        //查看是否已经创建该项目小组任务
        GroupTask groupTask1 = groupTaskDao.getGroupTaskByGpIdAndName(groupTask.getGroupId(), groupTask.getTaskName());

        if(groupTask1 != null){
            return new Result(ResultCode.PJTASK_GROUPTASK_ALREADY_EXISTED);
        }
        groupTaskDao.addGroupTask(groupTask);

        //更新该小组对指定项目任务所发布的小组任务数
        GroupPjTask groupPjTask = groupPjTaskDao.getGroupPjTaskNum(groupTask.getProjectTaskId(), groupTask.getGroupId());
        if(groupPjTask == null){ //首次创建项目任务对应的小组任务
            groupPjTaskDao.addGroupPjTask(groupTask.getProjectTaskId(), groupTask.getGroupId(), 1);
        }
        else{
            groupPjTaskDao.updateGroupPjTaskNum(groupTask.getProjectTaskId(), groupTask.getGroupId(), groupPjTask.getGroupTaskNum()+1, groupPjTask.getGroupTaskFinishNum());
        }
        return Result.SUCCESS();
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 查看小组内任务列表
     * @param: Integer groupId
     * @return:
    */
    @Override
    public List<GroupTask> getPjGroupAllTasks(Integer groupId) {
        return groupTaskDao.getAllGroupTasks(groupId);
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description:
     * @param:
     * @return:
    */
    @Override
    public GroupTask getPjGroupTask() {
        return null;
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description:
     * @param:
     * @return:
    */
    @Override
    public boolean deletePjGroupTask(int pjTaskId, int groupId, int groupTaskId, boolean isFinished) {
        groupTaskDao.deleteGroupTaskByTaskId(groupTaskId);

        //更新该小组与项目任务的关联信息
        GroupPjTask groupPjTask = groupPjTaskDao.getGroupPjTaskNum(pjTaskId, groupId);
        if(groupPjTask == null){
            return false;
        }
        if(isFinished){  //删除已完成的项目小组任务
            groupPjTaskDao.updateGroupPjTaskNum(pjTaskId, groupId, groupPjTask.getGroupTaskNum()-1, groupPjTask.getGroupTaskFinishNum()-1);
        }
        else{
            groupPjTaskDao.updateGroupPjTaskNum(pjTaskId, groupId, groupPjTask.getGroupTaskNum()-1, groupPjTask.getGroupTaskFinishNum());
        }
        return true;
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 组员更新组内任务完成情况
     * @param: Integer groupId
     * @return: boolean
    */
    @Override
    public boolean updateGroupTask(int pjTaskId, int groupId, int groupTaskId) {
        groupTaskDao.updateGruopTaskFinished(groupTaskId, 1);

        //更新该小组与项目任务的关联信息
        GroupPjTask groupPjTask = groupPjTaskDao.getGroupPjTaskNum(pjTaskId, groupId);
        if(groupPjTask == null){
            return false;
        }

        groupPjTaskDao.updateGroupPjTaskNum(pjTaskId, groupId, groupPjTask.getGroupTaskNum(),
                groupPjTask.getGroupTaskFinishNum()+1);

        //该项目任务小组已经完成
        if(groupPjTask.getGroupTaskNum() == (groupPjTask.getGroupTaskFinishNum() + 1)){
            groupPjTaskDao.updateGroupPjTaskFinished(pjTaskId, groupId, 1);
        }
        return true;
    }
}
