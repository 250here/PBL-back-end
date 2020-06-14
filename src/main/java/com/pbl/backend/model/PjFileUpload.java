package com.pbl.backend.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

/**
 * @author: 杜东方
 * @date: 2020/6/13
 * @description:
 */
@Data
public class PjFileUpload {

    private String userId;
    private Integer courseId;
    private Integer projectId;
    private MultipartFile multipartFile;
}
