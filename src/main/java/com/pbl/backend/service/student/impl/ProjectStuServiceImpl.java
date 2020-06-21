package com.pbl.backend.service.student.impl;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.dao.GroupDao;
import com.pbl.backend.dao.ProjectDao;
import com.pbl.backend.dao.ProjectScoreDao;
import com.pbl.backend.entity.Group;
import com.pbl.backend.entity.Project;
import com.pbl.backend.entity.ProjectScore;
import com.pbl.backend.model.StuEvaluate;
import com.pbl.backend.model.StuPjEvaluation;
import com.pbl.backend.service.student.IProjectStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/6/1
 * @description:
 */
@Service
public class ProjectStuServiceImpl implements IProjectStuService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectScoreDao projectScoreDao;
    @Autowired
    private GroupDao groupDao;


    /**
     * @author:  杜东方
     * @date: 2020/6/1
     * @description: 获取课程所有项目
     * @param: Integer courseId
     * @return: List<Project>
    */
    @Override
    public List<Project> getCourseAllProjects(Integer courseId) {
        return projectDao.getAllProject(courseId);
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 根据项目ID获取项目
     * @param: Integer projectId
     * @return: Project
    */
    @Override
    public Project getCourseProject(Integer projectId) {
        return projectDao.getProjectById(projectId);
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 学生加入项目
     * @param: Integer projectId, String userId
     * @return: boolean
    */
    @Override
    public boolean joinProject(Integer projectId, String userId) {
        ProjectScore projectScore = projectScoreDao.getPjScoreByPjIdAndStuId(projectId, userId);
        if(projectScore != null){ //学生已经加入该项目
            return false;
        }
        projectScoreDao.addStuProjectInfo(projectId, userId, 0, 0);
        return true;
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 学生退出项目
     * @param: Integer projectId, String userId
     * @return: boolean
    */
    @Override
    public boolean dropProject(Integer projectId, String userId) {
        projectScoreDao.deleteStuProjectInfo(projectId, userId);
        return true;
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/20
     * @description: 学生获取该课程下已经加入的项目
     * @param:
     * @return:
    */
    @Override
    public List<Project> getMyCourseProject(String userId, int courseId){
        return projectDao.getMyCourseProject(userId, courseId);
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/21
     * @description: 更新学生互评成绩
     * @param:
     * @return:
    */
    @Override
    public Result updateStuGrade(StuPjEvaluation stuPjEvaluation, String userId) {
        if(stuPjEvaluation == null || stuPjEvaluation.getProjectId() == null || stuPjEvaluation.getGroupId() == null){
            return Result.FAIL();
        }
        List<StuEvaluate> stuEvaluate = stuPjEvaluation.getStuEvaluates();
        if(stuEvaluate == null || stuEvaluate.size() == 0){
            return Result.FAIL();
        }

        Group group = groupDao.getGroupByGroupId(stuPjEvaluation.getGroupId());
        int members = group.getGroupMembers().size();
        for(StuEvaluate temp : stuEvaluate){
            //获取当前学生成绩
            ProjectScore projectScore = projectScoreDao.getPjScoreByPjIdAndStuId(stuPjEvaluation.getProjectId(), temp.getUserId());
            int grade = (int)1.0/members*temp.getGrade()+projectScore.getStuGrade();
            projectScoreDao.updateStuEvaluate(stuPjEvaluation.getProjectId(), temp.getUserId(), grade);
        }

        //标记该学生已完成评测
        projectScoreDao.updateFinishedEvaluate(stuPjEvaluation.getProjectId(), userId);
        return Result.SUCCESS();
    }
}
