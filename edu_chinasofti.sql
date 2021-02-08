/*
 Navicat Premium Data Transfer

 Source Server         : 我的阿里云
 Source Server Type    : MySQL
 Source Server Version : 100038
 Source Host           : 47.106.102.217:3306
 Source Schema         : edu_chinasofti

 Target Server Type    : MySQL
 Target Server Version : 100038
 File Encoding         : 65001

 Date: 08/02/2021 17:05:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_chinasofti_class
-- ----------------------------
DROP TABLE IF EXISTS `t_chinasofti_class`;
CREATE TABLE `t_chinasofti_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_estonian_ci NULL DEFAULT NULL COMMENT '创建时间',
  `start_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_estonian_ci NULL DEFAULT NULL COMMENT '开班时间',
  `end_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_estonian_ci NULL DEFAULT NULL COMMENT '毕业时间',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_estonian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `t_id` int(11) NULL DEFAULT NULL COMMENT '老师id',
  `status` int(11) NULL DEFAULT NULL COMMENT '0:尚未开班 1.学习中 2.学习完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_estonian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_chinasofti_class
-- ----------------------------
INSERT INTO `t_chinasofti_class` VALUES (5, '2020-08-28', '2020-08-01', '2020-08-31', 'java002', 1, 2);
INSERT INTO `t_chinasofti_class` VALUES (6, '2020-08-28', '2020-08-01', '2020-08-07', 'java003', 3, 2);
INSERT INTO `t_chinasofti_class` VALUES (7, '2020-08-28', '2020-08-01', '2020-12-29', 'java0302', 3, 1);
INSERT INTO `t_chinasofti_class` VALUES (8, '2020-08-31', '2020-08-01', '2020-08-31', 'java0831', 1, 0);
INSERT INTO `t_chinasofti_class` VALUES (10, '2020-08-31', '2020-09-11', '2021-06-18', 'java007', 2, 0);
INSERT INTO `t_chinasofti_class` VALUES (15, '2020-11-08 20:07:31', '2020-11-01', '2020-11-12', 'web1', 17, 1);
INSERT INTO `t_chinasofti_class` VALUES (16, '2020-11-08 20:08:20', '2020-11-11', '2020-11-13', 'web2', 6, 0);
INSERT INTO `t_chinasofti_class` VALUES (17, '2020-11-08 20:09:05', '2020-11-20', '2020-11-19', 'web3', 17, 1);
INSERT INTO `t_chinasofti_class` VALUES (18, '2020-11-08 20:09:24', '2020-11-21', '2020-11-27', 'web4', 2, 2);
INSERT INTO `t_chinasofti_class` VALUES (19, '2020-11-08 20:09:48', '2020-11-20', '2020-11-30', 'web5', 5, 0);
INSERT INTO `t_chinasofti_class` VALUES (20, '2020-11-09 12:44:17', '2020-11-12', '2020-11-20', 'web6', 1, 1);

-- ----------------------------
-- Table structure for t_chinasofti_student
-- ----------------------------
DROP TABLE IF EXISTS `t_chinasofti_student`;
CREATE TABLE `t_chinasofti_student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `school` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校',
  `subject` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `education` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `sex` int(11) NULL DEFAULT NULL COMMENT '0男  1女',
  `status` int(11) NULL DEFAULT NULL COMMENT '0报名未学习 1报名已学习  2转班 3停学',
  `class_id` int(11) NULL DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_chinasofti_student
-- ----------------------------
INSERT INTO `t_chinasofti_student` VALUES (6, '2020-09-01', NULL, '学生3', '1fb1383c1f0a5904d9741420320ee1fa', '武汉理工大学', '软件信息工程', '本科', '13220872908', 0, 0, 7);
INSERT INTO `t_chinasofti_student` VALUES (8, '2020-09-01', '2020-09-01', '学生7', '1fb1383c1f0a5904d9741420320ee1fa', '湖北科技大学', '软件信息技术', '本科', '13609824567', 0, 1, 10);

-- ----------------------------
-- Table structure for t_chinasofti_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_chinasofti_teacher`;
CREATE TABLE `t_chinasofti_teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `sex` int(11) NULL DEFAULT NULL COMMENT '0 男  1女',
  `active` int(11) NULL DEFAULT NULL COMMENT '0 未带班 1带班中',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_chinasofti_teacher
-- ----------------------------
INSERT INTO `t_chinasofti_teacher` VALUES (24, '2020-12-17 20:32:59', NULL, '小军君老师', '92bee7c104068899c97c83afa6beb808', '15218480260', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
