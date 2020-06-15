package com.pbl.backend.service.teacher.impl;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.config.FileManageConfig;
import com.pbl.backend.dao.*;
import com.pbl.backend.entity.Project;
import com.pbl.backend.entity.ProjectScore;
import com.pbl.backend.service.common.IFileService;
import com.pbl.backend.service.teacher.IProjectService;
import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.FileExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/5/23
 * @description: 教师课程项目管理业务处理
 */
@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private ProjectTaskDao projectTaskDao;
    @Autowired
    private ProjectScoreDao projectScoreDao;
    @Autowired
    private IFileService fileService;
    @Autowired
    private GroupPjTaskDao groupPjTaskDao;

    //创建课程项目
    @Override
    public boolean createProject(Project project) {
        Project projectTmp = projectDao.getProjectByCourseIdAndName(project.getCourseId(), project.getProjectName());
        if(projectTmp != null){  //该课程存在同名项目
            return false;
        }
        //课程项目创建成功
        projectDao.addProject(project);

        //创建课程项目文件空间
        String localUploadPath = FileManageConfig.getUploadStoragePath() + ValueConsts.SEPARATOR + project.getCourseId() + ValueConsts.SEPARATOR +project.getProjectId();
        if(fileService.createProjectFileSpace(localUploadPath)){
            return true;
        }
        else {
            projectDao.deleteProject(project.getProjectId());
            return false;
        }
    }

    //获取课程所有项目
    @Override
    public List<Project> getCourseAllProjects(int courseId) {
        return projectDao.getAllProject(courseId);
    }

    //根据项目ID获取项目
    @Override
    public Project getCourseProject(int projectId) {
        return projectDao.getProjectById(projectId);
    }

    //根据项目ID删除项目
    @Override
    public boolean deleteProject(int projectId) {
        Project project = projectDao.getProjectById(projectId);
        if(project == null) { //项目不存在,删除失败
            return false;
        }

        //删除项目讨论信息
        //discussionDao.deleteDiscsByProjectId(projectId);

        //删除项目文件空间的所有信息
        //pjFileDao.deleteFilesByProjectId(projectId);

        //删除本地存储项目文件
        String localUploadPath = FileManageConfig.getUploadStoragePath() + ValueConsts.SEPARATOR + project.getCourseId() + ValueConsts.SEPARATOR +project.getProjectId();
        try{
            FileExecutor.deleteDirectory(new File(localUploadPath));
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        //删除项目任务分组信息
        //groupTaskDao.deleteGroupTaskByProjectTaskId(projectId);

        //删除项目任务信息
        projectTaskDao.deletePjTasksByProjectId(projectId);

        //删除项目分组信息
        groupDao.deleteGroupsByProjectId(projectId);
        //userGroupDao.deleteGroupsByProjectId(projectId);

        //删除项目学生成绩信息
        //projectScoreDao.deleteScoresByProjectId(projectId);

        //删除项目信息
        projectDao.deleteProject(projectId);

        //项目删除成功
        return true;
    }

    @Override
    public Result getGradeRefData(int projectId) {
        List<ProjectScore> projectScores = projectScoreDao.getPjScoreByPjId(projectId);

        //获取项目任务发布数
        int pjTaskNum = projectTaskDao.getPjTaskNum(projectId);
        int groupPjTaskFinishedNum = 0;
        for(ProjectScore projectScore : projectScores){
            //学生所在小组的任务完成情况
            groupPjTaskFinishedNum = groupPjTaskDao.getGroupPjTaskFinishedNum(projectScore.getGroupId(), projectId);

            projectScore.setPjTaskCompletion(groupPjTaskFinishedNum + "/" +pjTaskNum);
        }
        return Result.SUCCESS(projectScores);
    }

    @Override
    public boolean updateStuGrade(int projectId, String stuId, int grade) {
        return projectScoreDao.updateStuGrade(projectId, stuId, grade) > 0;
    }
}
