package com.pbl.backend.dao;




import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestDao {


    //上传图片路径
    void upload(@Param("userId")String userId , @Param("photoPath")String photoPath);



}

