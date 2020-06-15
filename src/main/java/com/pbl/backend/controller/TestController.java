package com.pbl.backend.controller;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.config.FileManageConfig;
import com.pbl.backend.dao.UserDao;
import com.pbl.backend.entity.User;
import com.pbl.backend.utils.FileCommonUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author: 杜东方
 * @date: 2020/6/13
 * @description:
 */
@RestController
public class TestController {

    @Autowired
    UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @PostMapping("/test")
    public Result get(MultipartFile uploadFile){
//        String psw = "123456";
//        return Result.SUCCESS(bCryptPasswordEncoder.encode(psw));
//        User user = userDao.loadUserByUsername("mc");
//        return Result.SUCCESS(user);
        String localUrl = FileManageConfig.getUploadStoragePath() + FileManageConfig.SEPARATOR  + "2" +
                FileManageConfig.SEPARATOR + 3 + FileManageConfig.SEPARATOR + uploadFile.getOriginalFilename();
        return Result.SUCCESS(localUrl);
    }



    @GetMapping("test1")
    public Result test1(){
        int resutl = userDao.updatePhoto("17302010059", "1253252455");
        return Result.SUCCESS(resutl);
    }

}
