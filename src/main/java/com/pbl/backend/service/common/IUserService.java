package com.pbl.backend.service.common;
import com.pbl.backend.common.response.Result;
import com.pbl.backend.entity.User;

/**
 * @author: 杜东方
 * @date: 2020/5/17
 * @description:
 */
public interface IUserService {

    boolean addUser(User user);

    //更新用户密码
    Result updatePasswordOFUser(User user, String role);

    //获取用户密码
    String getPassword(User user);


    //更新图片路径
    boolean updateUserPhoto(User user);

    //获取图片路径
    String getPhotoPath(User user);
}
