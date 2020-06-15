package com.pbl.backend.service.common.impl;


import com.alibaba.fastjson.JSONObject;
import com.pbl.backend.common.response.Result;
import com.pbl.backend.dao.UserDao;
import com.pbl.backend.entity.Audience;
import com.pbl.backend.entity.User;
import com.pbl.backend.service.common.IUserService;
import com.pbl.backend.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: 杜东方
 * @date: 2020/5/17
 * @description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private Audience audience;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean addUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.add(user);
        userDao.addRole(user.getId(), 3);
        return true;
    }

    //更新用户密码
    @Override
    public Result updatePasswordOFUser(User user, String role){
        //确认旧密码是否正确
        String password_old = getPassword(user);

        boolean flag = bCryptPasswordEncoder.matches(user.getPassword(),password_old);
        if(flag){

            user.setPassword_new(bCryptPasswordEncoder.encode(user.getPassword_new()));
            //成功更改
            if(userDao.updatePasswordOFUser(user.getId(),user.getPassword_new()) > 0){
                String token = JwtTokenUtil.createJWT(user.getId(), user.getUsername(), role, audience);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token",token);
                return Result.SUCCESS(jsonObject);
            }
        }
        return Result.FAIL();
    }

    //获取用户密码
    @Override
    public String getPassword(User user) {
        return userDao.getPassword(user.getId());
    }


    @Override
    public boolean updateUserPhoto(User user) {
        if(userDao.updatePhoto(user.getId(),user.getPhotoPath())>0){
            return true;
        }
        return false;
    }

    @Override
    public String getPhotoPath(User user) {
        String path = userDao.getPhoto(user.getId());
        return path;
    }
}
