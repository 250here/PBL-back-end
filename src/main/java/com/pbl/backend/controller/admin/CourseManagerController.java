package com.pbl.backend.controller.admin;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.common.response.ResultCode;
import com.pbl.backend.entity.Course;
import com.pbl.backend.service.admin.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/6/13
 * @description:
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin/CourseManagerController-课程管理-管理员模块")
public class CourseManagerController {

    @Autowired(required=false)
    ICourseService courseService;

    //获取所有课程信息
    @ApiOperation(value = "获取所有课程信息---是否分页待定")
    @GetMapping("/courseList")
    public Result getAllCourseList(){
        List<Course> courses = courseService.getAllCourses();
        if(courses == null)
            return new Result(ResultCode.RESULT_NULL);
        else
            return Result.SUCCESS(courses);

    }

    //搜索课程, 根据课程名称
    @ApiOperation(value = "搜索课程, 根据课程名")
    @GetMapping("/courseInfo")
    public Result getCourse(String courseName){
        return null;
    }

}
