package com.pbl.backend.controller.admin;

import com.pbl.backend.common.response.ResultCode;
import com.pbl.backend.entity.Course;
import com.pbl.backend.entity.User;
import com.pbl.backend.service.admin.ICourseAdminService;
import com.pbl.backend.service.admin.IUserManageService;
import com.pbl.backend.service.student.ICourseStuService;
import com.pbl.backend.service.teacher.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pbl.backend.common.response.Result;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/5/19
 * @description: 管理员用户管理
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin/UserManageController-用户管理-管理员模块")
public class UserManageController {

    @Autowired
    IUserManageService userManageService;

    @Autowired(required=false)
    ICourseStuService courseStuService;

    @Autowired(required=false)
    ICourseService courseService;

    @ApiOperation(value = "新增教师")
    @PostMapping("/teacherInfo")
    public Result addTeacher(@RequestBody User user){

        if(userManageService.addUser(user) && userManageService.addRole(user.getId(),2)){
            return Result.SUCCESS();
        }
        return Result.FAIL();
    }

    @ApiOperation(value = "新增学生")
    @PostMapping("/studentInfo")
    public Result addStudent(@RequestBody User user){
        if(userManageService.addUser(user) && userManageService.addRole(user.getId(),3)){
            return Result.SUCCESS();
        }
        return Result.FAIL();
    }

    @ApiOperation(value = "分页获取用户信息---待定")
    @GetMapping("/usersInfos")
    public Result getAllUsers(){
        List<User> users = userManageService.getAllUser();
        if(users == null)
            return Result.FAIL();
        return Result.SUCCESS(users);
    }

//    @ApiOperation(value = "根据用户名获取用户")
//    @GetMapping("/userInfo")
//    public Result searchUser(){
//        return null;
//    }

    @ApiOperation(value = "删除指定用户")
    @DeleteMapping("/userInfo/{userId}")
    public Result deleteUser(@PathVariable("userId") String userId){

        int role = userManageService.getUserRole(userId);

        //学生
        if(role == 3){
            List<Course> coursesOfStu = courseStuService.getJoinedCourseInfos(userId);
            if(coursesOfStu.size() != 0){
                System.out.println("+++++++");
                return Result.FAIL("该用户正在进行课程学习,删除失败");
            }
        }
        if(role == 2){
            //若是老师看是否教课
            List<Course> coursesOfTeacher = courseService.getCoursesOfTeacher(userId);

            if (coursesOfTeacher.size()!=0){
                System.out.println("============");
                return Result.FAIL("该用户正在进行课程教授，删除失败");
            }
        }

        if(userManageService.deleteUserRoleById(userId) && userManageService.deleteUserById(userId)){
            System.out.println("-----------");
            return Result.SUCCESS();
        }
        return Result.FAIL();
    }

    //搜索用户, 根据用户名
    @ApiOperation(value = "搜索用户, 根据用户名")
    @GetMapping("/userInfo/{userName}")
    public Result getUser(@PathVariable("userName")String userName){
        User user = userManageService.getUserByName(userName);
        if(user == null)
            return new Result(ResultCode.RESULT_NULL);

        return Result.SUCCESS(user);
    }

//    @ApiOperation(value = "修改用户，即修改角色——待定")
//    @PutMapping("/userInfo")
//    public Result updateUserInfo(){
//        return null;
//    }

}
