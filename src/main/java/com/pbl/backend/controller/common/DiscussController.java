package com.pbl.backend.controller.common;

import com.pbl.backend.entity.Audience;
import com.pbl.backend.entity.Discussion;
import com.pbl.backend.service.common.IProjectDiscussionService;
import com.pbl.backend.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pbl.backend.common.response.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 杜东方
 * @date: 2020/5/19
 * @description: 课程项目用户讨论
 */
@RestController
@RequestMapping("/course/project")
@Api(tags = "common/DiscussController-课程项目用户讨论-教师/学生共用模块")
public class DiscussController {

    @Autowired
    Audience audience;

    @Autowired
    private IProjectDiscussionService discussionService;

    @ApiOperation(value = "获取课程项目的讨论版主题信息")
    @GetMapping("/discussionList/{projectId}")
    public Result getPjDiscussions(@PathVariable("projectId") Integer projectId){
        return discussionService.getProjectDiscussions(projectId);
    }

    @ApiOperation(value = "获取课程项目的讨论版某个讨论的具体信息")
    @GetMapping("/discussionInfo/{discussionId}")
    public Result getPjDiscussion(@PathVariable("discussionId") Integer discussionId){
        return discussionService.getProjectDiscussion(discussionId);
    }

    @ApiOperation(value = "在课程项目内发起主题提问")
    @PostMapping("/discussion")
    public Result createDiscussion(HttpServletRequest request, @RequestBody Discussion discussion){
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);
        discussion.setUserId(userId);
        boolean result = discussionService.createProjectDiscussion(discussion);
        return result ? Result.SUCCESS() : Result.FAIL();
    }

    @ApiOperation(value = "在课程项目内某个讨论主题发起回复")
    @PostMapping("/discussionReply")
    public Result createDiscussionReply(HttpServletRequest request, @RequestBody Discussion discussion){
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);
        discussion.setUserId(userId);
        discussion.setDiscTitle(null);
        if(discussion.getDiscId() == null){
            return Result.FAIL();
        }
        boolean result = discussionService.createPJDiscussionReply(discussion);
        return result ? Result.SUCCESS() : Result.FAIL();
    }
}
