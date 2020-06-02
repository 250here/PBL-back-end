package com.pbl.backend.service.student.impl;

import com.pbl.backend.dao.ProjectDao;
import com.pbl.backend.dao.ProjectScoreDao;
import com.pbl.backend.entity.Project;
import com.pbl.backend.entity.ProjectScore;
import com.pbl.backend.service.student.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/6/1
 * @description:
 */
@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectScoreDao projectScoreDao;


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
        projectScoreDao.addStuProjectInfo(projectId, userId);
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
}
