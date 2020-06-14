package com.pbl.backend.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Group
 * @Description
 * @Author 孟超
 * @Date 2020/6/1
 **/

@Data
public class Group {
    private Integer groupId;
    private Integer projectId;
    private String groupName;
    private String groupHeaderId;

    List<User> groupMembers;
    public Group(){}

    public Group(Integer projectId, String groupName, String groupHeaderId){
        this.projectId = projectId;
        this.groupName = groupName;
        this.groupHeaderId = groupHeaderId;
    }

}
