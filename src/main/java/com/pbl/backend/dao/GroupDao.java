package com.pbl.backend.dao;

import com.pbl.backend.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupDao {

    //创建小组
    int addPjGroup(Group group);

    //根据ProjectId获取所有小组信息
    List<Group> getAllGroupsByPjId(@Param("projectId")int projectId);

    //根据GroupId获取小组信息
    Group getGroupByGroupId(@Param("groupId")int groupId);

    //根据ProjectId和GroupName获取group
    Group getGroupByPjIdAndName(@Param("projectId")int projectId, @Param("groupName")String groupName);

    //根据项目ID删除该项目所有小组信息
    int deleteGroupsByProjectId(@Param("projectId")int projectId);

    int getGroupIdByGroupNameAndPjId(@Param("projectId")int projectId, @Param("groupName")String groupName);
}
