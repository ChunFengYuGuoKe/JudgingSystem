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

 Date: 23/04/2024 11:19:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_records
-- ----------------------------
DROP TABLE IF EXISTS `tbl_records`;
CREATE TABLE `tbl_records`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '提交记录id',
  `homework_id` bigint(20) NOT NULL COMMENT '提交题目id',
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '使用语言',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交用户',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户解答文件路径',
  `score` int(11) NULL DEFAULT NULL COMMENT '用户得分',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `homework_id`(`homework_id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `language`(`language`) USING BTREE,
  CONSTRAINT `tbl_records_ibfk_2` FOREIGN KEY (`username`) REFERENCES `tbl_users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tbl_records_ibfk_3` FOREIGN KEY (`homework_id`) REFERENCES `tbl_homework` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_records
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
