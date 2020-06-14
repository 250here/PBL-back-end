package com.pbl.backend.service.common.impl;

import com.alibaba.fastjson.JSONObject;
import com.pbl.backend.common.response.Result;
import com.pbl.backend.dao.DiscussionDao;
import com.pbl.backend.entity.Discussion;
import com.pbl.backend.service.common.IProjectDiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/6/1
 * @description:
 */
@Service
public class ProjectDiscussionServiceImpl implements IProjectDiscussionService {

    @Autowired
    private DiscussionDao discussionDao;

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 获取课程项目的讨论版主题信息
     * @param:
     * @return:
    */
    @Override
    public Result getProjectDiscussions(Integer projectId) {
        return Result.SUCCESS(discussionDao.getDiscussionsTitle(projectId));
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 获取课程项目的讨论版某个讨论的具体信息
     * @param:
     * @return:
    */
    @Override
    public Result getProjectDiscussion(Integer discussionId) {
        Discussion discussion = discussionDao.getDiscussionById(discussionId);
        List<Discussion> discussionReply = discussionDao.getDiscussionReply(discussionId);
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("discussionTheme", discussion);
            jsonObject.put("discussionReply", discussionReply);
            return Result.SUCCESS(jsonObject);
        }
        catch (Exception e){
            return Result.FAIL();
        }
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 在课程项目内发起主题提问
     * @param:
     * @return:
    */
    @Override
    public boolean createProjectDiscussion(Discussion discussion) {
        return discussionDao.addDiscussion(discussion) > 0;
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 在课程项目内发起回复
     * @param:
     * @return:
     */
    @Override
    public boolean createPJDiscussionReply(Discussion discussion) {
        return discussionDao.addDiscussionReply(discussion) > 0;
    }
}
