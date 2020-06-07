package com.pbl.backend.dao;

import com.pbl.backend.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PjFileDao {

    int insert(File pjFile);

    //获取项目内所有文件资源，当前用户除外
    List<File> getAllFilesExcludeUser(@Param("projectId")Integer projectId, @Param("userId")String userId);

    //获取当前用户在项目内的所有文件资源
    List<File> getAllFilesWithUser(@Param("projectId")Integer projectId, @Param("userId") String userId);

    File getFileByFileId(@Param("fileId")Integer fileId);

    int deleteFileByFileId(@Param("fileId")Integer fileId);

    int deleteFilesByProjectId(@Param("projectId")Integer projectId);

}
