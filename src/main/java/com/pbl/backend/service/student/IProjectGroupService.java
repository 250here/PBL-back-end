package com.pbl.backend.service.student;

import com.pbl.backend.entity.Group;

import java.util.List;

/**
 * @author: 杜东方
 * @date: 2020/6/1
 * @description:
 */
public interface IProjectGroupService {

    List<Group> getPjAllGroups(Integer projectId);

    Group getPjGroup(Integer groupId);

    boolean createPjGroup(int projectId, String userId, String groupName);

    boolean joinPjGroup(Integer projectId, Integer groupId, String userId);

    boolean dropPjGroup(Integer groupId, String userId);


}
