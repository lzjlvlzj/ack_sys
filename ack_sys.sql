/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : easyoa

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2017-10-13 08:50:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ack_department
-- ----------------------------
DROP TABLE IF EXISTS `ack_department`;
CREATE TABLE `ack_department` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `departmentName` varchar(128) NOT NULL,
  `parentId` int(12) NOT NULL DEFAULT '-1' COMMENT '父id',
  `comments` varchar(256) DEFAULT NULL COMMENT '评论',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ack_employee_job_log
-- ----------------------------
DROP TABLE IF EXISTS `ack_employee_job_log`;
CREATE TABLE `ack_employee_job_log` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `userId` bigint(24) NOT NULL,
  `projectId` bigint(24) DEFAULT NULL,
  `departmentId` int(12) DEFAULT NULL,
  `content` varchar(256) NOT NULL,
  `color` varchar(128) DEFAULT NULL COMMENT '颜色',
  `cacheStatus` int(2) NOT NULL DEFAULT '0' COMMENT '是否是缓存 0 : 是, 1 : 不是 (是缓存的不会被当做日志查询出来)',
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '缓存创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ack_menu
-- ----------------------------
DROP TABLE IF EXISTS `ack_menu`;
CREATE TABLE `ack_menu` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(128) NOT NULL,
  `url` varchar(128) DEFAULT NULL,
  `menuType` int(4) DEFAULT NULL COMMENT '0 : 菜单 ,1 :  button',
  `parentId` int(12) DEFAULT NULL,
  `menuLevel` int(4) DEFAULT NULL,
  `css` varchar(256) DEFAULT NULL COMMENT 'css样式',
  `domId` varchar(64) DEFAULT NULL COMMENT 'dom元素id',
  `permission` varchar(128) DEFAULT NULL COMMENT '权限字符',
  `comments` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ack_project
-- ----------------------------
DROP TABLE IF EXISTS `ack_project`;
CREATE TABLE `ack_project` (
  `id` bigint(24) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '工程名字',
  `deptId` int(12) NOT NULL COMMENT '部门id',
  `managerId` bigint(24) NOT NULL COMMENT '工程管理人员，项目负责人',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '项目是否设置为公共(其他部门人员可以参与)  0 : 不公开 , 1 : 公开  ',
  `status` int(2) NOT NULL COMMENT '工程状态(0:打开,1:关闭)',
  `startTime` datetime DEFAULT NULL COMMENT '开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '结束时间',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `pid_deptId_fk` (`deptId`),
  CONSTRAINT `pid_deptId_fk` FOREIGN KEY (`deptId`) REFERENCES `ack_department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ack_project_dept
-- ----------------------------
DROP TABLE IF EXISTS `ack_project_dept`;
CREATE TABLE `ack_project_dept` (
  `id` bigint(24) NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `projectId` bigint(24) NOT NULL,
  `departmentId` bigint(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ack_uk_pid_did` (`projectId`,`departmentId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ack_project_user
-- ----------------------------
DROP TABLE IF EXISTS `ack_project_user`;
CREATE TABLE `ack_project_user` (
  `id` bigint(24) NOT NULL AUTO_INCREMENT COMMENT ' 数据库id',
  `projectId` bigint(24) NOT NULL,
  `userId` bigint(24) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ack_role
-- ----------------------------
DROP TABLE IF EXISTS `ack_role`;
CREATE TABLE `ack_role` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `roleName` varchar(128) NOT NULL COMMENT '角色名称',
  `viewStatus` int(1) NOT NULL DEFAULT '0' COMMENT '是否拥有查看所有数据的权限 : 0, 没有 1 , 有',
  `weight` int(2) NOT NULL DEFAULT '0' COMMENT '权重(角色高的可以任命角色低的)',
  `abbreviation` varchar(128) DEFAULT NULL COMMENT '简写',
  `menuIds` varchar(256) DEFAULT NULL,
  `comments` varchar(128) DEFAULT NULL COMMENT '备注',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ack_user
-- ----------------------------
DROP TABLE IF EXISTS `ack_user`;
CREATE TABLE `ack_user` (
  `id` bigint(24) NOT NULL AUTO_INCREMENT COMMENT '数据库id',
  `loginName` varchar(128) NOT NULL COMMENT '登录名称',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL DEFAULT '689' COMMENT '盐',
  `surname` varchar(128) DEFAULT NULL COMMENT '姓',
  `name` varchar(128) DEFAULT NULL COMMENT '名字',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '用户状态 0 : 可用 ,1 : 禁用',
  `roleids` varchar(64) DEFAULT '6' COMMENT '角色id',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `departmentId` int(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`loginName`) USING BTREE,
  KEY `user_dept_fk` (`departmentId`),
  CONSTRAINT `user_dept_fk` FOREIGN KEY (`departmentId`) REFERENCES `ack_department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
