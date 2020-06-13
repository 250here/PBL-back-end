package com.pbl.backend.controller.student;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.entity.Group;
import com.pbl.backend.service.student.IProjectStuGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/5/18
 * @description: 学生项目小组管理
 */
@RestController
@RequestMapping("/student/course/project/group")
@Api(tags = "student/StuProjectGroupController-学生项目小组管理-学生模块")
public class StuProjectGroupController {

    @Autowired
    private IProjectStuGroupService stuProjectGroupService;

    @ApiOperation(value = "获取该课程项目所有小组")
    @GetMapping("/pjGroupList/{projectId}")
    public Result getAllPjGroupList(@PathVariable("projectId") Integer projectId){
        List<Group> groups = stuProjectGroupService.getPjAllGroups(projectId);
        return Result.SUCCESS(groups);
    }

    @ApiOperation(value = "获取指定项目小组信息")
    @GetMapping("/pjGroupInfo/{groupId}")
    public Result getPjGroupInfo(@PathVariable("groupId") Integer groupId){
        Group group = stuProjectGroupService.getPjGroup(groupId);
        return Result.SUCCESS(group);
    }

    @ApiOperation(value = "创建小组")
    @PostMapping("/pjGroupInfo/{projectId}/{userId}/{groupName}")
    public Result getPjGroupInfo(@PathVariable("projectId") Integer projectId, @PathVariable("userId") String userId,
                                 @PathVariable("groupName") String groupName){
        boolean result = stuProjectGroupService.createPjGroup(projectId, userId, groupName);
        return result ? Result.SUCCESS() : Result.FAIL();
    }

    @ApiOperation(value = "加入项目小组")
    @PostMapping("/studentInfo/{projectId}/{groupId}/{userId}")
    public Result joinPjGroup(@PathVariable("projectId") Integer projectId, @PathVariable("groupId") Integer groupId,
                              @PathVariable("userId") String userId){
        boolean result = stuProjectGroupService.joinPjGroup(projectId, groupId, userId);
        return result ? Result.SUCCESS() : Result.FAIL();
    }

    @ApiOperation(value = "退出项目小组")
    @DeleteMapping("/studentInfo/{groupId}/{userId}")
    public Result dropPjGroup(@PathVariable("groupId") Integer groupId, @PathVariable("userId") String userId) {
        boolean result = stuProjectGroupService.dropPjGroup(groupId, userId);
        return result ? Result.SUCCESS() : Result.FAIL();
    }
}
