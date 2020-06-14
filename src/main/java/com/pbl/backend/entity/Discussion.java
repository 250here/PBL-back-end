package com.pbl.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @ClassName Disscussion
 * @Description
 * @Author 孟超
 * @Date 2020/6/1
 **/

@Data
public class Discussion {
    private Integer discId;
    private String userId;
    private String userName;
    private Integer pjId;
    private String discTitle;
    private String discMessage;

    @JsonIgnore
    public String getUserId() {
        return userId;
    }

    @JsonProperty
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonIgnore
    public Integer getPjId() {
        return pjId;
    }

    @JsonProperty
    public void setPjId(Integer pjId) {
        this.pjId = pjId;
    }
}
