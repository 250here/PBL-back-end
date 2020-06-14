package com.pbl.backend.dao;

import com.pbl.backend.entity.Discussion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussionDao {

    //根据项目ID删除该项目所有讨论主题
    int deleteDiscsByProjectId(@Param("projectId")int projectId);

    //根据项目ID删除该项目所有讨论回复
    int deleteDiscsReplyByProjectId(@Param("projectId")int projectId);

    //创建讨论
    int addDiscussion(Discussion discussion);

    //讨论回复
    int addDiscussionReply(Discussion discussion);

    //根据projectId获取项目所有讨论主题信息
    List<Discussion> getDiscussionsTitle(@Param("projectId") int projectId);

    Discussion getDiscussionById(@Param("discussionId") int discussionId);

    //根据discussionId获取某次的讨论主题的全部回复信息
    List<Discussion> getDiscussionReply(@Param("discussionId") int discussionId);
}
