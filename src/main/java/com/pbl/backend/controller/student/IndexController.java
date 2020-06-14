package com.pbl.backend.controller.student;

import com.pbl.backend.common.response.ResultCode;
import com.pbl.backend.entity.User;
import com.pbl.backend.service.common.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbl.backend.common.response.Result;

/**
 * @author: 杜东方
 * @date: 2020/5/18
 * @description: 学生注册
 */
@RestController
@RequestMapping("/student")
@Api(tags = "student/IndexController-学生注册-学生模块")
public class IndexController {

    @Autowired
    IUserService userService;

    @ApiOperation(value = "学生注册")
    @PostMapping("/register")
    public Result register(@RequestBody User user){

        if(userService.addUser(user))
            return Result.SUCCESS();

        return new Result(ResultCode.FAIL);
    }
}
