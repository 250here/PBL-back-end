package com.pbl.backend.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.pbl.backend.common.response.Result;

/**
 * @author: 杜东方
 * @date: 2020/5/19
 * @description: 管理员用户管理
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin/UserManageController-用户管理-管理员模块")
public class UserManageController {

    @ApiOperation(value = "新增教师")
    @PostMapping("/teacherInfo")
    public Result addTeacher(){
        return null;
    }

    @ApiOperation(value = "新增学生")
    @PostMapping("/studentInfo")
    public Result addStudent(){
        return null;
    }

    @ApiOperation(value = "分页获取用户信息---待定")
    @GetMapping("/usersInfos")
    public Result getAllUsers(){
        return null;
    }

//    @ApiOperation(value = "根据用户名获取用户")
//    @GetMapping("/userInfo")
//    public Result searchUser(){
//        return null;
//    }

    @ApiOperation(value = "删除指定用户")
    @DeleteMapping("/userInfo")
    public Result deleteUser(){
        return null;
    }

    //搜索用户, 根据用户名
    @ApiOperation(value = "搜索用户, 根据用户名")
    @GetMapping("/userInfo")
    public Result getUser(String userName){
        return null;
    }

//    @ApiOperation(value = "修改用户，即修改角色——待定")
//    @PutMapping("/userInfo")
//    public Result updateUserInfo(){
//        return null;
//    }

}
