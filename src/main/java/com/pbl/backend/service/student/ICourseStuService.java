package com.pbl.backend.service.student;

public interface ICourseStuService {

    boolean studentTakesCourse(String userId, int courseId);

    boolean studentDropCourse(String userId, int courseId);
}
