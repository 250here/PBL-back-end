package com.pbl.backend.service.common.impl;

import com.alibaba.fastjson.JSONObject;
import com.pbl.backend.BackendApplication;
import com.pbl.backend.common.response.Result;
import com.pbl.backend.config.FileManageConfig;
import com.pbl.backend.constant.ConfigConstants;
import com.pbl.backend.dao.PjFileDao;
import com.pbl.backend.entity.PjFile;
import com.pbl.backend.service.common.IFileService;
import com.pbl.backend.utils.FileCommonUtils;
import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.FileExecutor;
import com.zhazhapan.util.Formatter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author: 杜东方
 * @date: 2020/5/24
 * @description:
 */
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    PjFileDao pjFileDao;

    @Override
    public boolean upload(String courseId, String userId, String projectId, MultipartFile multipartFile) {
        String name = multipartFile.getOriginalFilename();

        String suffix = FileExecutor.getFileSuffix(name);

        //创建上传文件的本地存储名,保证其唯一
        String uniqueName = UUID.randomUUID().toString().replaceAll("-", "") + "-" + userId + ValueConsts.DOT_SIGN +suffix;

        String localUrl = FileManageConfig.getUploadStoragePath() + ValueConsts.SEPARATOR + courseId +
                ValueConsts.SEPARATOR + projectId + ValueConsts.SEPARATOR + uniqueName;

        long uploadFileSize = multipartFile.getSize();
        String uploadMaxSizeTag = BackendApplication.file_manage_settings.getJSONObject(ConfigConstants.FILE_MAX_SIZE_OF_SETTING[0])
                .getString(ConfigConstants.FILE_MAX_SIZE_OF_SETTING[1]);
        long uploadMaxSize = Formatter.sizeToLong(uploadMaxSizeTag);

        boolean fileExists = localUrlExists(localUrl);

        //是否可以上传
        boolean canUpload = !multipartFile.isEmpty() && uploadFileSize <= uploadMaxSize && !fileExists;


        if(canUpload){
            try{
                File file = new File(localUrl);
                multipartFile.transferTo(file);
                //将文件信息插入数据库
                int isSuccess = pjFileDao.insert(new PjFile(userId, Integer.valueOf(projectId), name, courseId+"/"+projectId+"/"+uniqueName));
                if(isSuccess < 1){
                    FileExecutor.deleteFile(localUrl);
                    return false;
                }
                return true;
            }
            catch (Exception e){
                FileExecutor.deleteFile(localUrl);
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean localUrlExists(String localUrl) {
        //return fileDAO.checkLocalUrl(localUrl) > 0;
        return false;
    }


    @Override
    public String[] getFileResource(int fileId) {
        PjFile pjFile = pjFileDao.getFileByFileId(fileId);
        if(pjFile == null){
            return null;
        }
        return new String[]{FileCommonUtils.getRealLocalPath(pjFile.getFilePath()), pjFile.getFileName()};
    }


    @Override
    public boolean addPjFile(PjFile pjFile) {
        if(pjFileDao.insert(pjFile) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean createCourseFileSpace(String path){
        return FileExecutor.createFolder(path);
    }

    @Override
    public boolean createProjectFileSpace(String path) {
        return FileExecutor.createFolder(path);
    }

    @Override
    public Result getAllPjFileResources(int projectId, String userId) {
        //项目内他人分享资源
        List<PjFile> pjFilesOthers = pjFileDao.getAllFilesExcludeUser(projectId, userId);
        //项目内当前用户分享资源
        List<PjFile> pjMyFiles = pjFileDao.getAllFilesWithUser(projectId, userId);

        JSONObject data = new JSONObject();
        data.put("otherFileResources", pjFilesOthers);
        data.put("myFileResources", pjMyFiles);
        return Result.SUCCESS(data);
    }

    @Override
    public Result deleteFileResource(int fileId, String userId) {
        PjFile pjFile = pjFileDao.getFileByFileId(fileId);
        if(pjFile == null || StringUtils.isBlank(userId) || !StringUtils.equals(pjFile.getUserId(), userId)){
            return Result.FAIL();
        }

        FileExecutor.deleteFile(FileCommonUtils.getRealLocalPath(pjFile.getFilePath()));

        if(pjFileDao.deleteFileByFileId(fileId) > 0){
            return Result.SUCCESS();
        }

        return Result.FAIL();
    }

}
