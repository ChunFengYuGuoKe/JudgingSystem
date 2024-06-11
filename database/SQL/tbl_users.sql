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

 Date: 01/06/2024 18:15:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_users
-- ----------------------------
DROP TABLE IF EXISTS `tbl_users`;
CREATE TABLE `tbl_users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名（学生学号、教师工号）',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `identity` int(11) NOT NULL COMMENT '用户身份（0学生 1教师）',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE COMMENT '用户名唯一',
  INDEX `class`(`class`) USING BTREE,
  CONSTRAINT `tbl_users_ibfk_1` FOREIGN KEY (`class`) REFERENCES `tbl_class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_users
-- ----------------------------
INSERT INTO `tbl_users` VALUES (1, '2021141410001', '123456', 1, '张三', NULL, '123@scu.edu.cn');
INSERT INTO `tbl_users` VALUES (2, '2021141420001', '123456', 0, '李四', '2110101', NULL);
INSERT INTO `tbl_users` VALUES (3, '2021141420002', '123456', 0, '王五', '2110101', NULL);
INSERT INTO `tbl_users` VALUES (4, '2021141420003', '123456', 0, 'Michael', '2110102', '666@gmail.com');

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `tbl_class`;
CREATE TABLE `tbl_class`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `class_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级号',
  `teacher` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `class_id`(`class_id`) USING BTREE,
  INDEX `teacher`(`teacher`) USING BTREE,
  CONSTRAINT `tbl_class_ibfk_1` FOREIGN KEY (`teacher`) REFERENCES `tbl_users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_class
-- ----------------------------
INSERT INTO `tbl_class` VALUES (1, '2110101', '2021141410001');
INSERT INTO `tbl_class` VALUES (2, '2110102', '2021141410001');

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
DROP TABLE IF EXISTS `tbl_homework`;
CREATE TABLE `tbl_homework`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据库自增id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业标题',
  `id_lang` int(11) NOT NULL COMMENT '使用语言',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目描述',
  `solution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参考答案文件路径',
  `usecases` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试用例文件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_homework
-- ----------------------------
INSERT INTO `tbl_homework` VALUES (1, 'a+b', 1, '输入两个数a和b,计算a加b的和', NULL, NULL);
INSERT INTO `tbl_homework` VALUES (2, 'n皇后', 1, 'n皇后', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE IF EXISTS `tbl_records`;
CREATE TABLE `tbl_records`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '提交记录id',
  `id_homework` bigint(20) NOT NULL COMMENT '提交题目id',
  `id_lang` int(11) NOT NULL COMMENT '使用语言',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交用户',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户解答文件路径',
  `score` int(11) NULL DEFAULT NULL COMMENT '用户得分',
  `black` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试用例通过点',
  `white` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'AST符合度描述',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `language`(`id_lang`) USING BTREE,
  INDEX `homework_id`(`id_homework`) USING BTREE,
  CONSTRAINT `tbl_records_ibfk_2` FOREIGN KEY (`username`) REFERENCES `tbl_users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tbl_records_ibfk_3` FOREIGN KEY (`id_homework`) REFERENCES `tbl_homework` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_records
-- ----------------------------
INSERT INTO `tbl_records` VALUES (1, 1, 1, '2021141420001', '0', 0, 'test', 'test', '2024-05-15 09:30:00');
INSERT INTO `tbl_records` VALUES (2, 1, 1, '2021141420002', '10', 10, NULL, NULL, '2024-05-15 09:31:00');
INSERT INTO `tbl_records` VALUES (5, 2, 1, '2021141420001', '0', 5, NULL, NULL, '2024-05-15 09:32:00');

SET FOREIGN_KEY_CHECKS = 1;