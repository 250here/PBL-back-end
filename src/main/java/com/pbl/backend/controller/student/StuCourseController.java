package com.pbl.backend.controller.student;

import com.pbl.backend.entity.Audience;
import com.pbl.backend.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pbl.backend.common.response.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 杜东方
 * @date: 2020/5/18
 * @description: 学生课程管理
 */
@RestController
@RequestMapping("/student/course")
@Api(tags = "student/StuCourseController-学生课程管理-学生模块")
public class StuCourseController {

    @Autowired
    private Audience audience;

    @ApiOperation(value = "根据课程ID或名称获取课程信息")
    @GetMapping("/courseIndexInfo")
    public Result searchCourseInfo(){
        return null;
    }

    @ApiOperation(value = "加入课程")
    @PostMapping("/studentInfo/{courseId}")
    public Result joinCourse(HttpServletRequest request, @PathVariable("courseId") Integer courseId){
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);
        return null;
    }

    @ApiOperation(value = "退出课程")
    @DeleteMapping("/studentInfo")
    public Result dropCourse(){
        return null;
    }
}
