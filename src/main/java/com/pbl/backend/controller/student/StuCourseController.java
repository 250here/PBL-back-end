package com.pbl.backend.controller.student;

import com.pbl.backend.common.response.ResultCode;
import com.pbl.backend.entity.Audience;
import com.pbl.backend.entity.Course;
import com.pbl.backend.service.admin.ICourseAdminService;
import com.pbl.backend.service.student.ICourseStuService;
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

    @Autowired(required=false)
    ICourseStuService courseStuService;

    @Autowired(required=false)
    ICourseAdminService courseService;

    @ApiOperation(value = "根据课程名称获取课程信息")
    @GetMapping("/courseIndexInfo/{courseName}")
    public Result searchCourseInfo(@PathVariable("courseName")String courseName){
        Course course = courseService.getCourseByName(courseName);

        if(course == null)
            return new Result(ResultCode.RESULT_NULL);
        else
            return Result.SUCCESS(course);

    }

    @ApiOperation(value = "加入课程")
    @PostMapping("/studentInfo/takesCourse/{courseId}")
    public Result joinCourse(HttpServletRequest request, @PathVariable("courseId") Integer courseId){
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);

        if(courseStuService.studentTakesCourse(userId, courseId))
            return Result.SUCCESS();

        return new Result(ResultCode.TAKES_COURSE_FAIL);
    }

    @ApiOperation(value = "退出课程")
    @DeleteMapping("/studentInfo/dropCourse/{courseId}")
    public Result dropCourse(HttpServletRequest request, @PathVariable("courseId") Integer courseId){
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);

        if(courseStuService.studentDropCourse(userId, courseId))
            return Result.SUCCESS();

        return new Result(ResultCode.FAIL);
    }
}
