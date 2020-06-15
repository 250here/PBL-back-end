package com.pbl.backend.service.student.impl;

import com.pbl.backend.dao.GroupDao;
import com.pbl.backend.dao.UserGroupDao;
import com.pbl.backend.entity.Group;
import com.pbl.backend.service.student.IProjectStuGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/6/1
 * @description:
 */
@Service
public class ProjectStuGroupServiceImpl implements IProjectStuGroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private UserGroupDao userGroupDao;

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 获取项目所有小组信息
     * @param: Integer projectId
     * @return: List<Group>
    */
    @Override
    public List<Group> getPjAllGroups(Integer projectId) {
        return groupDao.getAllGroupsByPjId(projectId);
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 根据groupId获取小组具体信息
     * @param: Integer groupId
     * @return: Group+User
    */
    @Override
    public Group getPjGroup(Integer groupId) {
        return groupDao.getGroupByGroupId(groupId);
    }


    /**
     * @author: 杜东方
     * @date: 2020/6/2
     * @description: 创建项目小组
     * @param:
     * @return:
    */
    @Override
    public boolean createPjGroup(int projectId, String userId, String groupName) {
        //判断是否已经加入某个项目小组
//        int groupId = -1;
//        groupId = userGroupDao.getGroupIdByPjIdAndUserId(projectId, userId);
//        if(groupId > 0){
//            return false;
//        }
        if(userGroupDao.getGroupIdByPjIdAndUserId(projectId, userId) != null){
            return false;
        }

        Group group = groupDao.getGroupByPjIdAndName(projectId, groupName);
        if(group != null){  //项目下存在同名小组
            return false;
        }
        group = new Group(projectId, groupName, userId);
        //创建项目小组信息

        groupDao.addPjGroup(group);

        group.setGroupId(groupDao.getGroupIdByGroupNameAndPjId(projectId, groupName));
        System.out.println(projectId+"==="+ group.getGroupId()+"==="+userId);
        //关联学生与小组信息
        userGroupDao.addStuGroup(projectId, group.getGroupId(), userId);
        return true;
    }

    /**
     * @author: 杜东方
     * @date: 2020/6/1
     * @description: 加入某个项目小组
     * @param: Integer groupId, String userId
     * @return: boolean
    */
    @Override
    public boolean joinPjGroup(Integer projectId, Integer groupId, String userId) {
        int result = userGroupDao.addStuGroup(projectId, groupId, userId);
        return result > 0;
    }

    /**
     * @author:
     * @date: 2020/6/1
     * @description: 退出某个项目小组
     * @param: Integer groupId, String userId
     * @return: boolean
    */
    @Override
    public boolean dropPjGroup(Integer groupId, String userId) {
        int result = userGroupDao.deleteStuGroup(groupId, userId);
        return result > 0;
    }
}
