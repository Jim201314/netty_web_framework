/*
Navicat MySQL Data Transfer

Source Server         : 192.168.50.3
Source Server Version : 50546
Source Host           : 192.168.50.3:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2017-03-23 12:10:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cityname` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `pinying` varchar(20) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '杭州', 'hangzhou', '1');
INSERT INTO `city` VALUES ('2', '上海', 'shanghai', '1');
INSERT INTO `city` VALUES ('3', '北京', 'beijing', '1');
