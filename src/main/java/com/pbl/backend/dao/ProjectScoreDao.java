package com.pbl.backend.dao;

import com.pbl.backend.entity.ProjectScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectScoreDao {

    //根据项目ID删除该项目所有成绩信息
    int deleteScoresByProjectId(@Param("projectId")int projectId);

    //删除学生与某个项目的关联信息
    int deleteStuProjectInfo(@Param("projectId")int projectId, @Param("stuId")String stuId);

    //新增学生与某个项目的关联信息
    int addStuProjectInfo(@Param("projectId")int projectId, @Param("stuId")String stuId,@Param("studentGrade")int studentGrade,@Param("teacherGrade")int teacherGrade);

    //根据projectId和stuId,获取该学生成绩信息
    ProjectScore getPjScoreByPjIdAndStuId(@Param("projectId")int projectId, @Param("stuId")String stuId);

    List<ProjectScore> getPjScoreByPjId(@Param("projectId") int projectId);

    //更新学生参与度
    int updateParticipation(String userId, int projectId);

    //更新学生评分
    int updateStuGrade(int projectId, String stuId, int grade);

    //更新学生互评成绩
    int updateStuEvaluate(int projectId, String stuId, int grade);

    int updateFinishedEvaluate(int projectId, String stuId);
}
