package com.pbl.backend.controller.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pbl.backend.entity.Audience;
import com.pbl.backend.entity.User;
import com.pbl.backend.service.common.impl.UserServiceImpl;

import com.pbl.backend.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import com.pbl.backend.common.response.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author: 杜东方
 * @date: 2020/5/18
 * @description: 用户个人信息管理
 */
@RestController
@RequestMapping("/user")
@Api(tags = "common/UserInfoController-用户个人信息管理-管理员/教师/学生共用模块")
public class UserInfoController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    private Audience audience;

    @Value("${image.upload_path}")
    String uploadPath;
    @ApiOperation(value = "上传用户头像")
    @PostMapping("/userProfilePic")
    public Result addUserProfilePic(MultipartFile photo, HttpServletRequest request) throws IOException {
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);

        User user = new User();
        user.setId(userId);
        //判断用户是否上传了文件
        if(!photo.isEmpty()){
            String path = uploadPath;

            //获取文件后缀
            String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);

            final String fileName = userId + "."+ suffix;

            //限制文件上传的类型
            String contentType = photo.getContentType();

            if("image/png".equals(contentType) || "image/jpg".equals(contentType) || "image/jpeg".equals(contentType)){
                File file = new File(path,fileName);

                //完成文件的上传
                photo.transferTo(file);

                String path01 = fileName;
                user.setPhotoPath(path01);

                //上传数据库
                if(userService.updateUserPhoto(user)){
                    Result result = Result.SUCCESS();
                    return result;
                }
            } else {
                return Result.FAIL("图片格式不符合要求或图片大小不符合要求");
            }
        }
        return Result.FAIL();
    }

    @ApiOperation(value = "更新用户密码")
    @PutMapping("/pass")
    public Result updateUserPassword(HttpServletRequest request, @RequestBody JSONObject json){

        String newPassword = json.get("newPassword").toString();
        String oldPassword = json.get("oldPassword").toString();
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);

        User user = new User();
        user.setId(userId);
        user.setPassword_new(newPassword);
        user.setPassword(oldPassword);

        String role = JwtTokenUtil.getUserRoleFromToken(request, audience);

        return userService.updatePasswordOFUser(user,role);
    }

    @ApiOperation(value = "加载用户头像")
    @GetMapping("/photo")
    public Result loadPhoto(HttpServletRequest request) throws IOException {
        String userId = JwtTokenUtil.getUserIdFromToken(request, audience);
        User user = new User();
        user.setId(userId);

        String photoPath = userService.getPhotoPath(user);
        Result result;
        if(photoPath != null){
            result = Result.SUCCESS(photoPath);
        }else {
            result = Result.FAIL();
        }
        return result;

    }


//    /**
//     * 更改用户信息，不包含密码和头像
//     */
//    @ApiOperation(value = "更新管理员信息-不包含密码和头像")
//    @PutMapping("/adminInfo")
//    public Result updateAdminInfo(){
//        return null;
//    }
//
//    @ApiOperation(value = "更新老师个人信息-不包含密码和头像")
//    @PutMapping("/teacherInfo")
//    public Result updateTeacherInfo(){
//        return null;
//    }
//
//    @ApiOperation(value = "更新学生个人信息-不包含密码和头像")
//    @PutMapping("/studentInfo")
//    public Result updateStuInfo(){
//        return null;
//    }

}
