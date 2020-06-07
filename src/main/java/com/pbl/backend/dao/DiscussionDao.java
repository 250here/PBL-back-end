package com.pbl.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DiscussionDao {

    //根据项目ID删除该项目所有讨论
    int deleteDiscsByProjectId(@Param("projectId")int projectId);
}
