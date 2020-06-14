package com.pbl.backend.service.common;

import com.pbl.backend.common.response.Result;
import com.pbl.backend.entity.Discussion;

public interface IProjectDiscussionService {

    Result getProjectDiscussions(Integer projectId);

    Result getProjectDiscussion(Integer discussionId);

    boolean createProjectDiscussion(Discussion discussion);

    boolean createPJDiscussionReply(Discussion discussion);
}
