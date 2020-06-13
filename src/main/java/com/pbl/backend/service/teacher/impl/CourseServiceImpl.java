package com.pbl.backend.service.teacher.impl;

import com.pbl.backend.config.FileManageConfig;
import com.pbl.backend.dao.*;
import com.pbl.backend.entity.Course;
import com.pbl.backend.entity.CourseApply;
import com.pbl.backend.entity.Project;
import com.pbl.backend.service.common.IFileService;
import com.pbl.backend.service.teacher.ICourseService;
import com.pbl.backend.service.teacher.IProjectService;
import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.FileExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CourseAdminServiceImpl
 * @Description
 * @Author 孟超
 * @Date 2020/6/3
 **/

@Service
@Slf4j
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private TakesDao takesDao;
    @Autowired
    private IProjectService projectService;

    @Autowired
    IFileService fileService;

    @Override
    public boolean createCourse(Course course) {
        log.info("创建课程: " + course.getCourseName());
        Course courseTmp = courseDao.getCourse(course.getTeacherId(), course.getCourseName());

        //存在相同课程
        if(courseTmp != null){
            log.info("存在同名课程: " + course.getCourseName());
            return false;
        }
        //添加进course
        courseDao.addCourse(course);

        //创建课程文件空间
        String localUploadPath = FileManageConfig.getUploadStoragePath() + ValueConsts.SEPARATOR + course.getCourseId();
        if(fileService.createCourseFileSpace(localUploadPath)){
            return true;
        }
        return true;
    }

    @Override
    public boolean deleteCourse(int courseId) {
        Course courseTmp = courseDao.getCourseByCourseId(courseId);

        if(courseTmp == null){
            return false;
        }

        //获取该课程下的project
        List<Project> listPj = projectDao.getAllProject(courseId);
        //删除pj信息及其他信息
        for (Project project: listPj){
            projectService.deleteProject(project.getProjectId());
        }

        //删除学生所选课程
        courseDao.deleteStudentTake(courseId);

        //删除该课程
        courseDao.deleteCourse(courseId);

        //删除该课程文件空间
        FileExecutor.createFolder(FileManageConfig.getUploadStoragePath() + ValueConsts.SEPARATOR + courseId);

        return true;
    }

    @Override
    public List<Course> getCoursesOfTeacher(String teacherId) {
        List<Course> courses = courseDao.getCourseByTeacherId(teacherId);
        return courses;
    }

    @Override
    public Course getCourseByCourseId(int courseId){
        Course course = courseDao.getCourseByCourseId(courseId);
        return course;
    }

    @Override
    public List<CourseApply> getAllCourseApply(int courseId){
        List<CourseApply> list = applyDao.getCourseApply(courseId);
        return list;
    }

    @Override
    public boolean handleApply(String userId, int courseId,String code) {

        if(code.equals("1")){
            if(applyDao.updateApply(userId, courseId,code) > 0 &&  takesDao.deleteCourse(userId,courseId) > 0){
                return true;
            }else {
                return false;
            }
        }
        if(code.equals("2")){
            if(applyDao.updateApply(userId, courseId,code) > 0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
}
