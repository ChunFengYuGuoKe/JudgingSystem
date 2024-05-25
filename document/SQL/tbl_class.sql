/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : ojdb

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 17/04/2024 09:12:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_class
-- ----------------------------
DROP TABLE IF EXISTS `tbl_class`;
CREATE TABLE `tbl_class`  (
  `class_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级号',
  `teacher_id` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师id',
  PRIMARY KEY (`class_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `tbl_class_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `tbl_users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_class
-- ----------------------------
INSERT INTO `tbl_class` VALUES ('2110101', '2021141410001');

SET FOREIGN_KEY_CHECKS = 1;
