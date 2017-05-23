/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : ack_sys

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2017-05-23 15:30:21
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ack_department
-- ----------------------------
INSERT INTO `ack_department` VALUES ('1', 'xxxx科技有限公司', '-1', 'xxxx科技有限公司测试', '2017-04-11 09:40:46');
INSERT INTO `ack_department` VALUES ('2', '财务部', '1', '财务部', '2017-04-11 10:23:14');
INSERT INTO `ack_department` VALUES ('3', '技术部', '1', '技术部', '2017-04-11 10:23:34');
INSERT INTO `ack_department` VALUES ('4', '销售部', '1', '销售部', '2017-04-11 10:23:44');
INSERT INTO `ack_department` VALUES ('5', '人事部', '1', '人事部', '2017-04-11 10:24:12');
INSERT INTO `ack_department` VALUES ('6', '宣传部', '1', '宣传部', '2017-04-11 10:32:46');
INSERT INTO `ack_department` VALUES ('7', '公关部', '6', '公关部', '2017-04-11 10:33:09');
INSERT INTO `ack_department` VALUES ('8', '无线部', '3', '无线部', '2017-04-11 10:33:30');
INSERT INTO `ack_department` VALUES ('9', '网站部', '3', '网站部', '2017-04-11 10:33:51');
INSERT INTO `ack_department` VALUES ('10', '产品部', '1', '产品部', '2017-04-11 10:34:19');
INSERT INTO `ack_department` VALUES ('11', '后勤部', '1', '后勤部', '2017-04-11 10:35:03');
INSERT INTO `ack_department` VALUES ('12', '行政部', '1', '行政部123', '2017-04-11 10:35:18');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ack_menu
-- ----------------------------
INSERT INTO `ack_menu` VALUES ('1', '用户管理', '/user/list/ui', '0', '0', '0', 'ace-icon fa fa-users', 'user-mem', 'user:list', '管理用户信息', '2017-04-24 11:23:49');
INSERT INTO `ack_menu` VALUES ('2', '用户添加', '', '1', '1', null, '', '', 'user:add', '添加用户', '2017-04-24 11:23:52');
INSERT INTO `ack_menu` VALUES ('3', '用户修改', '', '1', '1', null, '', '', 'user:update', '修改用户', '2017-04-24 11:23:54');
INSERT INTO `ack_menu` VALUES ('4', '用户删除', '', '1', '1', null, '', '', 'user:delete', '用户删除', '2017-04-24 11:23:57');
INSERT INTO `ack_menu` VALUES ('5', '角色管理', '/role/list/ui', '0', '0', '0', 'ace-icon fa fa-user', 'role-mem', 'role:list', '管理角色信息', '2017-04-24 11:24:00');
INSERT INTO `ack_menu` VALUES ('6', '角色添加', '', '1', '5', null, '', '', 'role:add', '添加角色', '2017-04-24 11:24:03');
INSERT INTO `ack_menu` VALUES ('7', '角色修改', '', '1', '5', null, '', '', 'role:update', '修改角色', '2017-04-24 11:24:06');
INSERT INTO `ack_menu` VALUES ('8', '角色删除', '', '1', '5', null, '', '', 'role:delete', '角色删除', '2017-04-24 11:24:08');
INSERT INTO `ack_menu` VALUES ('9', '菜单管理', '/menu/list/ui', '0', '0', '0', 'ace-icon fa fa-cog', 'menu-mem', 'menu:list', '管理菜单信息', '2017-04-24 11:24:11');
INSERT INTO `ack_menu` VALUES ('10', '菜单添加', '', '1', '9', null, '', '', 'menu:add', '添加菜单', '2017-04-24 11:24:13');
INSERT INTO `ack_menu` VALUES ('11', '菜单修改', '', '1', '9', null, '', '', 'menu:update', '修改菜单', '2017-04-24 11:24:16');
INSERT INTO `ack_menu` VALUES ('12', '菜单删除', '', '1', '9', null, '', '', 'menu:delete', '菜单删除', '2017-04-24 11:24:19');
INSERT INTO `ack_menu` VALUES ('13', '部门管理', '/dept/list/ui', '0', '0', '0', 'ace-icon fa fa-asterisk', 'dept-mem', 'dept:list', '部门管理', '2017-04-24 15:28:05');
INSERT INTO `ack_menu` VALUES ('14', '部门添加', null, '1', '13', null, null, null, 'dept:add', '部门添加', '2017-04-24 15:28:41');
INSERT INTO `ack_menu` VALUES ('15', '部门修改', null, '1', '13', null, null, null, 'dept:update', '部门修改', '2017-04-24 15:29:19');
INSERT INTO `ack_menu` VALUES ('16', '部门删除', null, '1', '13', null, null, null, 'dept:delete', '部门删除1', '2017-04-24 15:29:49');
INSERT INTO `ack_menu` VALUES ('17', 'dashborad1', '/user/dashboard', '0', '0', null, 'ace-icon fa fa-tachometer', 'dashborad-mem', 'user:dashboard', 'dashborad', '2017-04-25 12:42:53');
INSERT INTO `ack_menu` VALUES ('18', '用户角色', '', '1', '1', null, '', '', 'user:role', '为用户添加角色信息', '2017-05-18 14:41:48');
INSERT INTO `ack_menu` VALUES ('19', '角色菜单', '', '1', '5', null, '', '', 'role:menu', '为角色分配菜单', '2017-05-18 14:56:26');
INSERT INTO `ack_menu` VALUES ('20', '部门查看', '', '1', '13', null, '', '', 'dept:find', '查看部门信息', '2017-05-22 09:08:57');

-- ----------------------------
-- Table structure for ack_permission
-- ----------------------------
DROP TABLE IF EXISTS `ack_permission`;
CREATE TABLE `ack_permission` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `permissionName` varchar(128) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `authType` int(4) NOT NULL DEFAULT '0' COMMENT '页面url',
  `isleaf` int(4) NOT NULL DEFAULT '0' COMMENT '功能url',
  `parentId` int(12) NOT NULL DEFAULT '1' COMMENT '菜单父id',
  `flag` varchar(128) NOT NULL,
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ack_permission
-- ----------------------------
INSERT INTO `ack_permission` VALUES ('1', '用户管理', '0', '0', '-1', 'user-mem', '用户管理', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('2', '用户添加', '1', '1', '1', 'user-add', '用户添加', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('3', '用户修改', '1', '1', '1', 'user-update', '用户修改', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('4', '用户删除', '1', '1', '1', 'user-del', '用户删除', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('5', '角色管理', '0', '0', '-1', 'role-mem', '角色管理', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('6', '角色添加', '1', '1', '5', 'role-add', '角色添加', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('7', '角色修改', '1', '1', '5', 'role-update', '角色修改', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('8', '角色删除', '1', '1', '5', 'role-del', '角色删除', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('9', '菜单管理', '0', '0', '-1', 'menu-mem', '菜单管理', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('10', '菜单添加', '1', '1', '1', 'menu-add', '菜单添加', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('11', '菜单修改', '1', '1', '1', 'menu-update', '菜单修改', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('12', '菜单删除', '1', '1', '1', 'menu-del', '菜单删除', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('13', '部门管理', '0', '0', '-1', 'dept-mem', '部门管理', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('14', '部门添加', '1', '1', '13', 'dept-add', '部门添加', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('15', '部门修改', '1', '1', '13', 'dept-update', '部门修改', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('16', '部门删除', '1', '1', '13', 'dept-del', '部门删除', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('17', '休假管理', '0', '0', '-1', 'leave-mem', '休假管理', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('18', '请假记录', '0', '0', '17', 'leave-list', '请假记录', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('19', '已审批', '0', '0', '18', 'leave-pass', '已审批', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('20', '未审批', '0', '0', '19', 'leave-notapprove', '未审批', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('21', '查看', '1', '1', '20', 'leave-view', '查看', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('22', '审批', '1', '1', '21', 'leave-approve', '审批', '2017-04-05 09:52:24');
INSERT INTO `ack_permission` VALUES ('23', '权限管理', '0', '0', '-1', 'authority-mem', '权限管理', '2017-04-05 10:29:24');
INSERT INTO `ack_permission` VALUES ('24', '权限添加', '0', '1', '23', 'authority-add', '权限添加', '2017-04-05 10:32:06');
INSERT INTO `ack_permission` VALUES ('25', '权限修改', '0', '0', '1', 'authority-update', '权限修改', '2017-04-06 14:16:07');
INSERT INTO `ack_permission` VALUES ('27', '用户角色', '0', '0', '1', 'user-role2user', '用户角色', '2017-04-12 16:34:09');

-- ----------------------------
-- Table structure for ack_role
-- ----------------------------
DROP TABLE IF EXISTS `ack_role`;
CREATE TABLE `ack_role` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '数据id',
  `roleName` varchar(128) NOT NULL COMMENT '角色名称',
  `menuIds` varchar(128) DEFAULT NULL,
  `comments` varchar(128) DEFAULT NULL COMMENT '备注',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ack_role
-- ----------------------------
INSERT INTO `ack_role` VALUES ('1', 'admin', '1,2,3,4,18,5,6,7,8,19,9,10,11,12,13,14,15,16,20,17', '有系统管理权限没有业务权限3', '2017-03-29 16:30:29');
INSERT INTO `ack_role` VALUES ('2', 'cto', '1,2,3,4,13,14,15,16,17', '公司首席技术官', '2017-03-30 10:40:37');
INSERT INTO `ack_role` VALUES ('3', 'cfo', '17', '公司首席财务官', '2017-03-30 10:40:37');
INSERT INTO `ack_role` VALUES ('4', 'ceo', '17', '首席执行官', '2017-03-30 10:42:34');
INSERT INTO `ack_role` VALUES ('5', 'coo', '17', '首席运营官', '2017-03-30 10:43:08');
INSERT INTO `ack_role` VALUES ('6', '普通员工', '17', '普通员工', '2017-04-13 08:31:25');
INSERT INTO `ack_role` VALUES ('7', '部门经理', '1,2,13,15,20,17', '部门经理', '2017-04-24 10:04:34');

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
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `roleids` varchar(64) DEFAULT '6' COMMENT '角色id',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`loginName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ack_user
-- ----------------------------
INSERT INTO `ack_user` VALUES ('5', 'zhangsan', '2ffbc947b4af8c60b9df51dfca44d71d', '689', '张', '三', '0', '1', '2017-03-23 17:57:36');
INSERT INTO `ack_user` VALUES ('6', 'lisi', '2ffbc947b4af8c60b9df51dfca44d71d', '689', '张0', '三0', '0', '7', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('7', 'wangwu', '2ffbc947b4af8c60b9df51dfca44d71d', '689', '张1', '三1', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('8', 'zhangsan2', '123', '689', '张2', '三2', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('9', 'zhangsan3', '123', '689', '张3', '三3', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('10', 'zhangsan4', '123', '689', '张4', '三4', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('11', 'zhangsan5', '123', '689', '张5', '三5', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('12', 'zhangsan6', '123', '689', '张6', '三6', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('13', 'zhangsan7', '123', '689', '张7', '三7', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('14', 'zhangsan8', '123', '689', '张8', '三8', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('15', 'zhangsan9', '123', '689', '张9', '三9', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('16', 'zhangsan10', '123', '689', '张10', '三10', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('17', 'zhangsan11', '123', '689', '张11', '三11', '0', '6', '2017-03-27 16:12:44');
INSERT INTO `ack_user` VALUES ('18', 'zsan', '2ffbc947b4af8c60b9df51dfca44d71d', '689', '张12', '三12', '0', '7', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('19', 'zhangsan13', '123', '689', '张13', '三13', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('20', 'zhangsan14', '123', '689', '张14', '三14', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('21', 'zhangsan15', '123', '689', '张15', '三15', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('22', 'zhangsan16', '123', '689', '张16', '三16', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('23', 'zhangsan17', '123', '689', '张17', '三17', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('24', 'zhangsan18', '123', '689', '张18', '三18', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('25', 'zhangsan19', '123', '689', '张19', '三19', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('26', 'zhangsan20', '123', '689', '张20', '三20', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('27', 'zhangsan21', '123', '689', '张21', '三21', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('28', 'zhangsan22', '123', '689', '张22', '三22', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('29', 'zhangsan23', '123', '689', '张23', '三23', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('30', 'zhangsan24', '123', '689', '张24', '三24', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('31', 'zhangsan25', '123', '689', '张25', '三25', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('32', 'zhangsan26', '123', '689', '张26', '三26', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('33', 'zhangsan27', '123', '689', '张27', '三27', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('34', 'zhangsan28', '123', '689', '张28', '三28', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('35', 'zhangsan29', '123', '689', '张29', '三29', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('36', 'zhangsan30', '123', '689', '张30', '三30', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('37', 'zhangsan31', '123', '689', '张31', '三31', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('38', 'zhangsan32', '123', '689', '张32', '三32', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('39', 'zhangsan33', '123', '689', '张33', '三33', '0', '6', '2017-03-27 16:12:45');
INSERT INTO `ack_user` VALUES ('40', 'zhangsan34', '123', '689', '张34', '三34', '0', '6', '2017-03-27 16:12:45');
