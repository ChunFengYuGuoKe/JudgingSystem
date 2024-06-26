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

 Date: 15/06/2024 20:01:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_homework
-- ----------------------------
DROP TABLE IF EXISTS `tbl_homework`;
CREATE TABLE `tbl_homework`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据库自增id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业标题',
  `language` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '使用语言',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目描述',
  `solution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参考答案文件路径',
  `usecases` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试用例文件路径',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `language`(`language`) USING BTREE,
  CONSTRAINT `tbl_homework_ibfk_1` FOREIGN KEY (`language`) REFERENCES `tbl_plugins` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_homework
-- ----------------------------
INSERT INTO `tbl_homework` VALUES (1, 'a+b', 'Java', '输入两个数a和b,计算a加b的和', NULL, NULL);
INSERT INTO `tbl_homework` VALUES (2, 'n皇后', 'Java', 'n皇后', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
