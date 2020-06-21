/*
 Navicat Premium Data Transfer

 Source Server         : mysql02
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 139.196.199.177:3306
 Source Schema         : pbl

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 21/06/2020 18:27:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` int(0) NOT NULL,
  `apply_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`user_id`, `course_id`) USING BTREE,
  INDEX `apply_ibfk_2`(`course_id`) USING BTREE,
  CONSTRAINT `apply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `apply_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int(0) NOT NULL AUTO_INCREMENT,
  `teacher_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_startime` date NOT NULL,
  `course_endtime` date NOT NULL,
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `course_ibfk_1`(`teacher_id`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for discussion
-- ----------------------------
DROP TABLE IF EXISTS `discussion`;
CREATE TABLE `discussion`  (
  `discussion_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `project_id` int(0) NOT NULL,
  `discussion_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `discussion_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`discussion_id`, `user_id`, `project_id`) USING BTREE,
  INDEX `discussion_ibfk_2`(`user_id`) USING BTREE,
  INDEX `discussion_id`(`discussion_id`) USING BTREE,
  INDEX `discussion_ibfk_1`(`project_id`) USING BTREE,
  CONSTRAINT `discussion_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `discussion_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for discussion_reply
-- ----------------------------
DROP TABLE IF EXISTS `discussion_reply`;
CREATE TABLE `discussion_reply`  (
  `discussion_id` int(0) NOT NULL,
  `project_id` int(0) NOT NULL,
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `discussion_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  INDEX `discussion_reply_ibfk_2`(`project_id`) USING BTREE,
  INDEX `discussion_reply_ibfk_1`(`discussion_id`) USING BTREE,
  CONSTRAINT `discussion_reply_ibfk_1` FOREIGN KEY (`discussion_id`) REFERENCES `discussion` (`discussion_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `discussion_reply_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `file_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `project_id` int(0) NOT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `upload_time` date NOT NULL,
  PRIMARY KEY (`file_id`) USING BTREE,
  INDEX `file_ibfk_1`(`project_id`) USING BTREE,
  INDEX `file_ibfk_2`(`user_id`) USING BTREE,
  CONSTRAINT `file_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `file_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for group_project_task
-- ----------------------------
DROP TABLE IF EXISTS `group_project_task`;
CREATE TABLE `group_project_task`  (
  `project_task_id` int(0) NOT NULL,
  `group_id` int(0) NOT NULL,
  `group_task_num` int(0) NOT NULL DEFAULT 0,
  `group_task_finishnum` int(0) NOT NULL DEFAULT 0,
  `is_finished` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`project_task_id`, `group_id`) USING BTREE,
  INDEX `group_project_task_ibfk_1`(`group_id`) USING BTREE,
  CONSTRAINT `group_project_task_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `grouping` (`group_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `group_project_task_ibfk_2` FOREIGN KEY (`project_task_id`) REFERENCES `project_task` (`task_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for group_task
-- ----------------------------
DROP TABLE IF EXISTS `group_task`;
CREATE TABLE `group_task`  (
  `task_id` int(0) NOT NULL AUTO_INCREMENT,
  `project_task_id` int(0) NOT NULL,
  `group_id` int(0) NOT NULL,
  `task_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_discribe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_startime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `task_endtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `is_finished` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`task_id`) USING BTREE,
  INDEX `group_task_ibfk_1`(`group_id`) USING BTREE,
  INDEX `group_task_ibfk_2`(`project_task_id`) USING BTREE,
  CONSTRAINT `group_task_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `grouping` (`group_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `group_task_ibfk_2` FOREIGN KEY (`project_task_id`) REFERENCES `project_task` (`task_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for grouping
-- ----------------------------
DROP TABLE IF EXISTS `grouping`;
CREATE TABLE `grouping`  (
  `group_id` int(0) NOT NULL AUTO_INCREMENT,
  `project_id` int(0) NOT NULL,
  `group_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `group_header` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`group_id`) USING BTREE,
  INDEX `grouping_ibfk_1`(`project_id`) USING BTREE,
  CONSTRAINT `grouping_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `project_id` int(0) NOT NULL AUTO_INCREMENT,
  `course_id` int(0) NOT NULL,
  `project_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `project_startime` date NOT NULL,
  `project_endtime` date NOT NULL,
  `project_dsc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `evaluation_time` date NOT NULL,
  `teacher_eva_weight` int(0) NOT NULL,
  `stu_eva_weight` int(0) NOT NULL,
  PRIMARY KEY (`project_id`) USING BTREE,
  INDEX `project_ibfk_1`(`course_id`) USING BTREE,
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for project_task
-- ----------------------------
DROP TABLE IF EXISTS `project_task`;
CREATE TABLE `project_task`  (
  `task_id` int(0) NOT NULL AUTO_INCREMENT,
  `project_id` int(0) NOT NULL,
  `task_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_discribe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_startime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `task_endtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`task_id`) USING BTREE,
  INDEX `project_task_ibfk_1`(`project_id`) USING BTREE,
  CONSTRAINT `project_task_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

INSERT INTO `role`(`role_id`, `role_name`) VALUES(1, ''ROLE_ADMIN'');
INSERT INTO `role`(`role_id`, `role_name`) VALUES(2, ''ROLE_TEACHER'');
INSERT INTO `role`(`role_id`, `role_name`) VALUES(3, ''ROLE_STUDENT'');
-- ----------------------------
-- Table structure for student_pj
-- ----------------------------
DROP TABLE IF EXISTS `student_pj`;
CREATE TABLE `student_pj`  (
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `project_id` int(0) NOT NULL,
  `participation` int(0) NOT NULL DEFAULT 0,
  `student_grade` int(0) NOT NULL,
  `teacher_grade` int(0) NOT NULL,
  `evaluate_flag` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`, `project_id`) USING BTREE,
  INDEX `student_pj_ibfk_2`(`project_id`) USING BTREE,
  CONSTRAINT `student_pj_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_pj_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for takes
-- ----------------------------
DROP TABLE IF EXISTS `takes`;
CREATE TABLE `takes`  (
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` int(0) NOT NULL,
  PRIMARY KEY (`user_id`, `course_id`) USING BTREE,
  INDEX `takes_ibfk_2`(`course_id`) USING BTREE,
  CONSTRAINT `takes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `takes_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `photo_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group`  (
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `group_id` int(0) NOT NULL,
  `project_id` int(0) NOT NULL,
  PRIMARY KEY (`user_id`, `group_id`) USING BTREE,
  INDEX `user_group_ibfk_3`(`project_id`) USING BTREE,
  INDEX `user_group_ibfk_2`(`group_id`) USING BTREE,
  CONSTRAINT `user_group_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_group_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `grouping` (`group_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `user_group_ibfk_3` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` int(0) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `user_role_ibfk_2`(`role_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
