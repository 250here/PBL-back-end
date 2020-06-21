package com.pbl.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserGroupDao {

    //根据项目ID删除该项目所有小组成员信息
    int deleteGroupsByProjectId(@Param("projectId")int projectId);

    //学生加入小组,添加关联信息
    int addStuGroup(@Param("projectId")int projectId, @Param("groupId")int groupId, @Param("userId")String userId);

    //学生退出小组，删除关联信息
    int deleteStuGroup(@Param("groupId")int groupId, @Param("userId")String userId);

    //学生退出项目,删除所有小组关联信息
    int deleteStuPjGroup(@Param("projectId")int projectId, @Param("userId")String userId);


    //根据UserId和ProjectId获取groupId
    Integer getGroupIdByPjIdAndUserId(@Param("projectId")int projectId, @Param("userId")String userId);
}
