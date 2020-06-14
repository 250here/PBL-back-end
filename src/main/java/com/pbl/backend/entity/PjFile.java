package com.pbl.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @ClassName PjFile
 * @Description
 * @Author 孟超
 * @Date 2020/6/1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PjFile {

    private Integer fileId;
    @JsonIgnore
    private String userId;
    @JsonIgnore
    private Integer projectId;
    private String fileName;
    @JsonIgnore
    private String filePath;
    private Date uploadTime;

    public PjFile(String userId, Integer projectId, String fileName, String filePath){
        this.userId = userId;
        this.projectId = projectId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadTime = new Date(System.currentTimeMillis());
    }

}
