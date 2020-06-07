package com.pbl.backend.dao;

import com.pbl.backend.entity.ProjectScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProjectScoreDao {

    //根据项目ID删除该项目所有成绩信息
    int deleteScoresByProjectId(@Param("projectId")int projectId);

    //删除学生与某个项目的关联信息
    int deleteStuProjectInfo(@Param("projectId")int projectId, @Param("stuId")String stuId);

    //新增学生与某个项目的关联信息
    int addStuProjectInfo(@Param("projectId")int projectId, @Param("stuId")String stuId);

    //根据projectId和stuId,获取该学生成绩信息
    int getPjScoreByPjIdAndStuId(@Param("projectId")int projectId, @Param("stuId")String stuId);
}
