/*
Navicat MySQL Data Transfer

Source Server         : DockerMysql
Source Server Version : 50711
Source Host           : 192.168.23.137:13306
Source Database       : dmz_inward

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-11-30 12:03:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dmz_login
-- ----------------------------
DROP TABLE IF EXISTS `dmz_login`;
CREATE TABLE `dmz_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` char(32) NOT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `passwd` varchar(64) DEFAULT NULL,
  `platform` int(4) DEFAULT NULL COMMENT '登录平台：手机，WEB',
  `has_passwd` int(4) DEFAULT NULL,
  `pwd_strength` int(4) DEFAULT NULL,
  `status` int(4) DEFAULT NULL COMMENT '登录状态：可登录，禁止登陆',
  `is_delete` bit(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dmz_login
-- ----------------------------
INSERT INTO `dmz_login` VALUES ('12', 'c5b50d808b134faab7e6ef5b4b139846', 'dmz', '0307', '0', '1', '0', '0', 'false', '2016-09-04 20:09:01', '2016-09-04 20:09:01');
INSERT INTO `dmz_login` VALUES ('13', 'a01806f0f70e4aac84dac58b334b7998', 'dmz', '0307', '0', '1', '0', '0', 'false', '2016-09-04 20:18:09', '2016-09-04 20:18:09');
INSERT INTO `dmz_login` VALUES ('101', 'f897de89adb240a8bc373d95bfcc81a2', 'dmz', '0307', '0', '1', '0', '0', 'false', '2016-11-10 16:38:33', '2016-11-10 16:38:33');
INSERT INTO `dmz_login` VALUES ('102', 'cb99c755c5a24e8f94c7f1f81ba9e6e3', 'dmz', '0307', '0', '1', '0', '0', 'false', '2016-11-10 16:38:49', '2016-11-10 16:38:49');
INSERT INTO `dmz_login` VALUES ('107', '176e5675b3064e4baad5cd58eaa6f1b6', 'dmz', '0307', '0', '1', '0', '0', 'false', '2016-11-11 11:35:05', '2016-11-11 11:35:05');
INSERT INTO `dmz_login` VALUES ('109', 'b352f790b6d14919903eb5ec6f03f85e', 'dmz', '0307', '0', '1', '0', '0', 'false', '2016-11-11 11:39:39', '2016-11-11 11:39:39');
INSERT INTO `dmz_login` VALUES ('111', 'c8f44b0bfcda45ffbbd4bd4c4d51e76e', 'dmz', '0307', '0', '1', '0', '0', 'false', '2016-11-11 11:42:24', '2016-11-11 11:42:24');
INSERT INTO `dmz_login` VALUES ('115', '5082240761cd4bfb807cd7c5f3596abf', 'dmz', '0307', '0', '1', '0', '0', 'false', '2016-11-23 17:24:29', '2016-11-23 17:24:29');
INSERT INTO `dmz_login` VALUES ('124', 'eecf70d0d4394a74a2b92266f0991374', '程萌', '527386108', '0', '1', '0', '0', 'false', '2016-11-24 15:16:23', '2016-11-24 15:16:23');
INSERT INTO `dmz_login` VALUES ('125', '036dfc2d09e94333b72c43ece24c098e', '程萌', '527386108', '0', '1', '0', '0', 'false', '2016-11-24 15:21:37', '2016-11-24 15:21:37');

-- ----------------------------
-- Table structure for dmz_user
-- ----------------------------
DROP TABLE IF EXISTS `dmz_user`;
CREATE TABLE `dmz_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` char(32) NOT NULL COMMENT '用户号',
  `gender` int(2) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `hobbys` varchar(255) DEFAULT NULL,
  `is_single` int(2) DEFAULT NULL,
  `is_straight` int(2) DEFAULT NULL,
  `photo_no` char(32) DEFAULT NULL COMMENT '照片编号',
  `video_no` char(32) DEFAULT NULL COMMENT '视频编号',
  `is_delete` bit(1) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dmz_user
-- ----------------------------
INSERT INTO `dmz_user` VALUES ('1', 'b78ebdf04c5443cba45411cbc936cf99', '0', '0.jpeg', '', '1', '2', '', '', 'false', '2016-04-19 17:58:46', '2016-04-19 17:58:46');
INSERT INTO `dmz_user` VALUES ('2', 'b78ebdf04c5443cba45411cbc936cf99', '1', '../../assets/Chrysanthemum.jpg', '', '1', '2', '', '', 'false', '2016-11-10 16:42:48', '2016-11-10 16:42:48');
INSERT INTO `dmz_user` VALUES ('3', 'b78ebdf04c5443cba45411cbc936cf99', '1', '../../assets/Chrysanthemum.jpg', '', '1', '2', '', '', 'false', '2016-11-11 11:49:04', '2016-11-11 11:49:04');
INSERT INTO `dmz_user` VALUES ('4', 'b78ebdf04c5443cba45411cbc936cf99', '1', '../../assets/Chrysanthemum.jpg', '', '1', '2', '', '', 'false', '2016-11-23 17:24:29', '2016-11-23 17:24:29');
INSERT INTO `dmz_user` VALUES ('5', 'b78ebdf04c5443cba45411cbc936cf99', '1', '../../assets/Chrysanthemum.jpg', '', '1', '2', '', '', 'false', '2016-11-28 16:30:25', '2016-11-28 16:30:25');
INSERT INTO `dmz_user` VALUES ('6', 'b78ebdf04c5443cba45411cbc936cf99', '1', '../../assets/Chrysanthemum.jpg', '', '1', '2', '', '', 'false', '2016-11-28 16:31:24', '2016-11-28 16:31:24');
INSERT INTO `dmz_user` VALUES ('12', 'test', '1', '../../assets/Chrysanthemum.jpg', '', '1', '2', '', '', 'false', '2016-11-28 16:40:04', '2016-11-28 16:40:04');
