package com.pbl.backend.dao;

import com.pbl.backend.entity.PjFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PjFileDao {

    int insert(PjFile pjFile);

    //获取项目内所有文件资源，当前用户除外
    List<PjFile> getAllFilesExcludeUser(@Param("projectId")Integer projectId, @Param("userId")String userId);

    //获取当前用户在项目内的所有文件资源
    List<PjFile> getAllFilesWithUser(@Param("projectId")Integer projectId, @Param("userId") String userId);

    PjFile getFileByFileId(@Param("fileId")Integer fileId);

    int deleteFileByFileId(@Param("fileId")Integer fileId);

    int deleteFilesByProjectId(@Param("projectId")Integer projectId);

}
