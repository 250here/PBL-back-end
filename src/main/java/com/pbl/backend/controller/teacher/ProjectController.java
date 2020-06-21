package com.pbl.backend.controller.teacher;

import com.pbl.backend.common.response.ResultCode;
import com.pbl.backend.entity.Project;
import com.pbl.backend.service.teacher.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pbl.backend.common.response.Result;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/5/18
 * @description: 教师项目管理
 */
@RestController
@RequestMapping("/teacher/course/project")
@Api(tags = "teacher/ProjectController-教师项目管理-教师模块")
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @ApiOperation(value = "新增项目")
    @PostMapping("/projectInfo")
    public Result createProject(@RequestBody Project project){
        boolean createResult = projectService.createProject(project);
        if(createResult)
            return Result.SUCCESS();
        else
            return new Result(ResultCode.COURSE_SAME_PROJECTNAME);
    }

    @ApiOperation(value = "获取该课程所有项目")
    @GetMapping("/projectList/{courseId}")
    public Result getAllProjectList(@PathVariable("courseId") Integer courseId){
        List<Project> projects = projectService.getCourseAllProjects(courseId);
        return Result.SUCCESS(projects);
    }

    @ApiOperation(value = "获取指定项目信息")
    @GetMapping("/projectInfo/{projectId}")
    public Result getProjectInfo(@PathVariable("projectId") Integer projectId){
        Project project = projectService.getCourseProject(projectId);
        return Result.SUCCESS(project);
    }

    @ApiOperation(value = "删除项目")
    @DeleteMapping("/projectInfo/projectId}")
    public Result deleteProject(@PathVariable("projectId") Integer projectId){
        boolean result = projectService.deleteProject(projectId);
        return result ? Result.SUCCESS() : Result.FAIL();
    }

    @ApiOperation(value = "教师评分，获取项目内所有学生参考数据(所在项目完成情况、讨论留言、小组互评)")
    @GetMapping("/gradeRefData/{projectId}")
    public Result getGradeRefData(@PathVariable("projectId") Integer projectId){
        return projectService.getGradeRefData(projectId);
    }

    @ApiOperation(value = "提交/更新学生评分")
    @PutMapping("/gradeData/{projectId}/{studentId}/{grade}")
    public Result gradeForStu(@PathVariable("projectId") Integer projectId, @PathVariable("studentId") String studentId,
                              @PathVariable("grade") Integer grade){
        boolean result = projectService.updateStuGrade(projectId, studentId, grade);
        return result ? Result.SUCCESS() : Result.FAIL();
    }
}
