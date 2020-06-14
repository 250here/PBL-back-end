package com.pbl.backend.service.common;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.entity.PjFile;
import com.pbl.backend.model.PjFileUpload;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    /**
     * 上传文件
     *
     * @return 是否上传成功
      */
    Result upload(PjFileUpload pjFileUpload);


    /**
     * 本地路径是否存在
     *
     * @param localUrl 本地路径
     *
     * @return {@link Boolean}
     */
    boolean localUrlExists(String localUrl);


    /**
     * 获取资源
     *
     * @param visitUrl 访问路径
     * @param request {@link HttpServletRequest}
     *
     * @return {@link PjFile}
     */
    String[] getFileResource(int fileId);


    /**
     * @author: 杜东方
     * @date: 2020/5/25
     * @description: 保存文件信息到数据库
     * @param:
     * @return:
    */
    boolean addPjFile(PjFile pjFile);

    /**
     * @author: 杜东方
     * @date: 2020/5/25
     * @description: 创建课程根文件空间
     * @param:
     * @return:
     */
    boolean createCourseFileSpace(String path);

    /**
     * @author: 杜东方
     * @date: 2020/5/25
     * @description: 创建课程项目根文件空间
     * @param:
     * @return:
     */
    boolean createProjectFileSpace(String path);


    /**
     * @author: 杜东方
     * @date: 2020/5/25
     * @description: 获取项目内所有文件资源
     * @param:
     * @return:
     */
    Result getAllPjFileResources(int projectId, String userId);


    /**
     * @author: 杜东方
     * @date: 2020/5/25
     * @description: 删除项目内用户文件资源
     * @param:
     * @return:
     */
    Result deleteFileResource(int fileId, String userId);
}
