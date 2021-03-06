package com.pbl.backend.controller.student;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.entity.Audience;
import com.pbl.backend.entity.Group;
import com.pbl.backend.service.student.IProjectStuGroupService;
import com.pbl.backend.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private Audience audience;

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

    @ApiOperation(value = "获取我的项目小组信息")
    @GetMapping("/myPjGroupInfo/{projectId}")
    public Result getMyPjGroupInfo(HttpServletRequest request, @PathVariable("projectId") Integer projectId){
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);
        Group group = stuProjectGroupService.getMyPjGroup(userId, projectId);
        return Result.SUCCESS(group);
    }

    @ApiOperation(value = "创建小组")
    @PostMapping("/pjGroupInfo/{projectId}/{groupName}")
    public Result getPjGroupInfo(@PathVariable("projectId") Integer projectId, HttpServletRequest request,
                                 @PathVariable("groupName") String groupName){
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);
        boolean result = stuProjectGroupService.createPjGroup(projectId, userId, groupName);
        return result ? Result.SUCCESS() : Result.FAIL();
    }

    @ApiOperation(value = "加入项目小组")
    @PostMapping("/studentInfo/{projectId}/{groupId}")
    public Result joinPjGroup(@PathVariable("projectId") Integer projectId, @PathVariable("groupId") Integer groupId,
                              HttpServletRequest request){
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);
        boolean result = stuProjectGroupService.joinPjGroup(projectId, groupId, userId);
        return result ? Result.SUCCESS() : Result.FAIL();
    }

    @ApiOperation(value = "退出项目小组")
    @DeleteMapping("/studentInfo/dropGroup/{groupId}")
    public Result dropPjGroup(@PathVariable("groupId") Integer groupId, HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);
        boolean result = stuProjectGroupService.dropPjGroup(groupId, userId);
        return result ? Result.SUCCESS() : Result.FAIL();
    }
}
