package com.pbl.backend.controller.student;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.entity.GroupTask;
import com.pbl.backend.entity.ProjectTask;
import com.pbl.backend.service.student.IPjStuGroupTaskService;
import com.pbl.backend.service.teacher.IProjectTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/5/18
 * @description: 学生项目任务管理
 */
@RestController
@RequestMapping("/student/course/project")
@Api(tags = "student/StuProjectTaskController-学生项目任务管理-学生模块")
public class StuProjectTaskController {

    @Autowired
    private IProjectTaskService projectTaskService;

    @Autowired
    private IPjStuGroupTaskService pjGroupTaskService;


    @ApiOperation(value = "查看项目内所有任务列表(该任务为教师发布)")
    @GetMapping("/pjTaskList/{projectId}")
    public Result getPjTaskList(@PathVariable("projectId") Integer projectId){
        List<ProjectTask> projectTasks = projectTaskService.getPjAllTasks(projectId);
        return Result.SUCCESS(projectTasks);
    }

    @ApiOperation(value = "查看项目内指定任务具体信息，本小组的任务完成情况(该任务为教师发布)")
    @GetMapping("/pjTaskInfo/{pjTaskId}")
    public Result getPjTaskInfo(@PathVariable("pjTaskId") Integer pjTaskId){
        ProjectTask projectTask = projectTaskService.getPjTask(pjTaskId);
        return Result.SUCCESS(projectTask);
    }

    @ApiOperation(value = "发布项目小组内任务(该任务为组长发布,在教师发布任务的基础上发布)")
    @PostMapping("/pjGroupTask")
    public Result addPjGroupTask(@RequestBody GroupTask groupTask){
        return pjGroupTaskService.createPjGroupTask(groupTask);
    }

    @ApiOperation(value = "查看项目小组内所有任务列表(该任务为组长发布)")
    @GetMapping("/pjGroupTaskList/{groupId}")
    public Result getPjGroupTaskList(@PathVariable("groupId") Integer groupId){
        List<GroupTask> groupTasks = pjGroupTaskService.getPjGroupAllTasks(groupId);
        return Result.SUCCESS(groupTasks);
    }

    @ApiOperation(value = "查看项目小组内指定任务具体信息，包含组员任务认领和完成情况")
    @GetMapping("/pjGroupTaskInfo/{projectTaskId}/{groupId}")
    public Result getPjGroupTaskInfo(@PathVariable("projectTaskId") int projectTaskId,
                                     @PathVariable("groupId") int groupId){
        List<GroupTask> groupTasks = pjGroupTaskService.getPjTaskGroupAllTasks(projectTaskId, groupId);
        return Result.SUCCESS(groupTasks);
    }

    @ApiOperation(value = "删除项目小组内发布的任务")
    @DeleteMapping("/pjGroupTaskInfo/{projectTaskId}/{groupId}/{groupTaskId}/{isFinished}")
    public Result deletePjGroupTask(@PathVariable("projectTaskId") Integer pjTaskId, @PathVariable("groupId") Integer groupId,
                                    @PathVariable("groupTaskId") Integer groupTaskId, @PathVariable("isFinished") Boolean isFinished){
        boolean result = pjGroupTaskService.deletePjGroupTask(pjTaskId, groupId, groupTaskId, isFinished);
        return result ? Result.SUCCESS() : Result.FAIL();
    }

    @ApiOperation(value = "组长更新教师发布的项目任务完成情况")
    @PutMapping("/pjTaskFinished")
    public Result updatePjTaskFinished(){
        return null;
    }

    @ApiOperation(value = "组员更新组长发布的任务完成情况")
    @PutMapping("/pjGroupTaskFinished/{projectTaskId}/{groupId}/{groupTaskId}")
    public Result updatePjGroupTaskFinished(@PathVariable("projectTaskId") Integer pjTaskId, @PathVariable("groupId") Integer groupId,
                                            @PathVariable("groupTaskId") Integer groupTaskId){
        boolean result = pjGroupTaskService.updateGroupTask(pjTaskId, groupId, groupTaskId);
        return result ? Result.SUCCESS() : Result.FAIL();
    }
}
