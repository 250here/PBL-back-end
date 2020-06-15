package com.pbl.backend.controller.common;

import com.pbl.backend.entity.User;
import com.pbl.backend.service.common.impl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import com.pbl.backend.common.response.Result;
import org.springframework.web.multipart.MultipartFile;

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
    UserServiceImpl userService = new UserServiceImpl();


//    @ApiOperation(value = "更新用户头像")
//    @PostMapping("/userProfilePic")
//    public Result updateUserProfilePic(@RequestBody MultipartFile photo, @RequestBody User user) throws IOException{
//
//
//        //判断用户是否上传了文件
//        if(!photo.isEmpty()){
//
//            //文件上传的地址
//            String path = uploadPath;
//            //String realPath = path.replace('/', '\\').substring(1,path.length());
//            //用于查看路径是否正确
//            System.out.println(path);
//
//            //获取文件的名称
//            final String fileName = photo.getOriginalFilename();
//
//            //限制文件上传的类型
//            String contentType = photo.getContentType();
//            if("image/png".equals(contentType) || "image/jpg".equals(contentType) ){
//                File file = new File(path,fileName);
//
//                //完成文件的上传
//                photo.transferTo(file);
//
//                String path01 = fileName;
//                user.setPhotoPath(path01);
//
//                //上传数据库
//                if(userService.updatePhoto(user)){
//                    Result result = Result.SUCCESS();
//                    System.out.println("图片更新成功!");
//                    return result;
//                }
//            } else {
//                return Result.FAIL("图片格式不符合要求");
//            }
//        }
//        return Result.FAIL();
//    }

    @Value("${image.upload_path}")
    String uploadPath;
    @ApiOperation(value = "上传用户头像")
    @PostMapping("/userProfilePic")
    public Result addUserProfilePic(MultipartFile photo) throws IOException {
//        UserServiceImpl userService = new UserServiceImpl();
        User user = new User();
        user.setId("17302010059");
        //判断用户是否上传了文件
        if(!photo.isEmpty()){
            String path = uploadPath;


            //用于查看路径是否正确
            System.out.println(path);

            //获取文件的名称
            final String fileName = photo.getOriginalFilename();

            //限制文件上传的类型
            String contentType = photo.getContentType();
            System.out.println(contentType);
            if("image/png".equals(contentType) || "image/jpg".equals(contentType) || "image/jpeg".equals(contentType)){
                File file = new File(path,fileName);

                //完成文件的上传
                photo.transferTo(file);

                String path01 = fileName;
                user.setPhotoPath(path01);
                System.out.println(user.getPhotoPath()+user.getId()+"==================");
                //上传数据库
                if(userService.upLoadPhoto(user)){
                    Result result = Result.SUCCESS();
                    System.out.println("图片上传成功!");
                    return result;
                }
            } else {
                return Result.FAIL("图片格式不符合要求");
            }
        }
        return Result.FAIL();
    }

    @ApiOperation(value = "更新用户密码")
    @PutMapping("/pass")
    public Result updateUserPassword(@RequestBody User user){

        //修改成功
        if(userService.updatePasswordOFUser(user)){
            return Result.SUCCESS();

        }
        return Result.FAIL();
    }

    @ApiOperation(value = "加载用户头像")
    @RequestMapping("/photo")
    public Result loadPhoto(@RequestBody User user) throws IOException {
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
