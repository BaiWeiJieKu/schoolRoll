/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : schoolroll

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-08-28 17:45:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `href` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/sys/user', 'md-cog', '用户管理', '0');
INSERT INTO `menu` VALUES ('2', '/sys/speciality', null, '专业管理', '0');
INSERT INTO `menu` VALUES ('3', '/sys/student', null, '学生管理', '0');
INSERT INTO `menu` VALUES ('4', '/sys/check', null, '学籍管理', '0');
INSERT INTO `menu` VALUES ('5', '/sys/state', null, '毕业管理', '0');
INSERT INTO `menu` VALUES ('6', '/sys/total', null, '人数统计', '0');
INSERT INTO `menu` VALUES ('7', '/sys/speciality', null, '专业管理', '1');
INSERT INTO `menu` VALUES ('8', '/sys/speciality', null, '专业管理', '3');
INSERT INTO `menu` VALUES ('9', '/sys/student', null, '学生管理', '3');

-- ----------------------------
-- Table structure for `speciality`
-- ----------------------------
DROP TABLE IF EXISTS `speciality`;
CREATE TABLE `speciality` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `numbering` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of speciality
-- ----------------------------
INSERT INTO `speciality` VALUES ('1', '2019-08-28 12:07:13', '', 'zhuan', '123');
INSERT INTO `speciality` VALUES ('2', '2019-08-28 12:21:47', '', 'ye', '222');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `entrance_time` datetime DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_state` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `operator` int(11) DEFAULT NULL,
  `operator_state` int(11) NOT NULL,
  `pass` int(11) DEFAULT NULL,
  `state_time` datetime DEFAULT NULL,
  `new_speciality_id` int(11) DEFAULT NULL,
  `speciality_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2m3v2i3qga624g0rnb9cfjq33` (`new_speciality_id`),
  KEY `FKt434hun0i4tv58xnfvkbttk7v` (`speciality_id`),
  KEY `FKk5m148xqefonqw7bgnpm0snwj` (`user_id`),
  CONSTRAINT `FK2m3v2i3qga624g0rnb9cfjq33` FOREIGN KEY (`new_speciality_id`) REFERENCES `speciality` (`id`),
  CONSTRAINT `FKk5m148xqefonqw7bgnpm0snwj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt434hun0i4tv58xnfvkbttk7v` FOREIGN KEY (`speciality_id`) REFERENCES `speciality` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('3', '234234234', '2019-08-28 15:44:44', '2019-08-28 15:44:44', '男', '1', '水电费', '3', '1', '2', '2019-08-28 16:40:41', '2', '2', '4');
INSERT INTO `student` VALUES ('4', '13546876', '2019-08-28 17:35:34', '2019-08-28 17:35:34', '女', '1', '史蒂夫', '3', '0', '0', '2019-08-28 17:40:17', null, '2', '4');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL,
  `parent_id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, 'admin', 'admin', '0', 'admin', '0');
INSERT INTO `user` VALUES ('2', '2019-08-28 12:02:11', '111', '111', '1', '111', '1');
INSERT INTO `user` VALUES ('3', '2019-08-28 12:02:41', '222', '222', '2', '222', '2');
INSERT INTO `user` VALUES ('4', '2019-08-28 15:11:05', '333', '333', '3', '333', '3');

-- ----------------------------
-- Table structure for `user_speciality`
-- ----------------------------
DROP TABLE IF EXISTS `user_speciality`;
CREATE TABLE `user_speciality` (
  `user_id` int(11) NOT NULL,
  `speciality_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`speciality_id`),
  UNIQUE KEY `UK_ks2yaj0mpfb2f4fupxsbubpp3` (`speciality_id`),
  CONSTRAINT `FK53fnke9d0qq20ehpk900lwqkv` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKhaykfcr7ii4fpj11t288rk4r0` FOREIGN KEY (`speciality_id`) REFERENCES `speciality` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_speciality
-- ----------------------------
INSERT INTO `user_speciality` VALUES ('4', '1');
INSERT INTO `user_speciality` VALUES ('4', '2');
