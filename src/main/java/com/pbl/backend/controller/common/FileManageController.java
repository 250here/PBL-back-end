package com.pbl.backend.controller.common;

import com.pbl.backend.model.PjFileUpload;
import com.pbl.backend.service.common.IFileService;
import com.pbl.backend.utils.FileCommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pbl.backend.common.response.Result;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: 杜东方
 * @date: 2020/5/19
 * @description: 项目内用户文件管理
 */
@RestController
@RequestMapping("/course/project")
@Api(tags = "common/FileManageController-项目内用户文件管理-教师/学生共用模块")
public class FileManageController {

    @Autowired
    private IFileService fileService;

    @ApiOperation(value = "用户上传项目分享文件")
    @PostMapping("/sharedFile")
    public Result uploadFile(PjFileUpload pjFileUpload){
        return fileService.upload(pjFileUpload);
    }

    @ApiOperation(value = "用户浏览项目分享文件")
    @GetMapping("/sharedFiles/{projectId}/{userId}")
    public Result getSharedFiles(@PathVariable("projectId") Integer projectId,@PathVariable("userId") String userId){
        return fileService.getAllPjFileResources(projectId, userId);
    }

    @ApiOperation(value = "用户下载项目分享文件")
    @GetMapping("/sharedFile/{fileId}")
    public void downloadSharedFile(HttpServletResponse response, @PathVariable("fileId") Integer fileId){
        String[] resourceLocalPath = fileService.getFileResource(fileId);
        boolean result = FileCommonUtils.loadResource(response, resourceLocalPath);
    }

    @ApiOperation(value = "用户删除项目分享文件")
    @DeleteMapping("/sharedFile/{fileId}")
    public Result deleteSharedFile(@PathVariable("fileId") Integer fileId, String userId){
        return fileService.deleteFileResource(fileId,userId);
    }
}
