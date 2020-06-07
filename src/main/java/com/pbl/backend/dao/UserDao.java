package com.pbl.backend.dao;


import com.pbl.backend.entity.CourseApply;
import com.pbl.backend.entity.Role;
import com.pbl.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    User loadUserByUsername(@Param("userName")String userName);

    Role findRoleByUserId(@Param("userId")Integer userId);

    void add(User user);

    //更新用户密码
    Integer updatePasswordOFUser(@Param("userId")String userId, @Param("passwordNew")String passwordNew);

    //获取用户旧密码
    String getPassword(@Param("userId")String userId);

    //上传图片路径
    Integer uploadPhoto(@Param("userId")String UserId, @Param("photoPath")String photoPath);

    //更新图片路径
    Integer updatePhoto(@Param("userId")String userId, @Param("photoPath")String photoPath);

    //获取图片
    String getPhoto(@Param("userId")String userId);


}
