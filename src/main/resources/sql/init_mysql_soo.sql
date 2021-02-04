/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 127.0.0.1:3306
 Source Schema         : init_test

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 04/02/2021 08:45:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
`id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口名称',
`menu_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口地址',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
`update_time` datetime(0) NOT NULL COMMENT '修改时间',
`menu_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单类型 data/接口 page/页面',
`parent_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单Id 只有页面菜单需要',
`system` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单所属系统',
`user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单创建者',
`level` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单层级 只有页面菜单需要',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '删除用户', '/user/delete', '2021-01-05 14:53:31', '2021-01-05 14:53:33', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('10', '暂停任务', '/task/pause', '2021-01-06 11:32:30', '2021-01-06 11:32:41', 'data', NULL, 'wooho1', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('11', '启动任务', '/task/run', '2021-01-06 11:32:32', '2021-01-06 11:32:43', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('12', '分页列表', '/sys/log/page', '2021-01-06 18:14:12', '2021-01-06 18:14:15', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('19cc7658c3ad1bd6cfb657a201e18710', '编辑菜单', '/menu/update', '2021-01-11 13:55:04', '2021-01-11 13:55:04', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('1e74f04e75c51fe5a00c8ee3308c5935', '删除角色', '/role/delete', '2021-01-11 13:56:31', '2021-01-11 13:56:31', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('2', '用户详情', '/user/detail', '2021-01-05 14:53:35', '2021-01-05 14:53:37', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('3', '用户列表', '/user/list', '2021-01-05 14:53:39', '2021-01-05 14:53:41', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('31266da86090d900bd873276fb737b28', '分页列表', '/menu/page', '2021-01-11 16:46:21', '2021-01-11 16:46:21', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('3e320d07d905c8fe8b3d325eddacfd88', '添加菜单', '/menu/save', '2021-01-11 13:54:54', '2021-01-11 13:54:54', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('4', '分页列表', '/user/page', '2021-01-05 14:53:43', '2021-01-05 14:53:45', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('5', '添加用户', '/user/save', '2021-01-05 14:53:47', '2021-01-05 14:53:49', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('6', '分页列表', '/task/page', '2021-01-06 11:32:16', '2021-01-06 11:32:18', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('7', '编辑任务', '/task/update', '2021-01-06 11:32:21', '2021-01-06 11:32:34', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('8', '新增任务', '/task/save', '2021-01-06 11:32:25', '2021-01-06 11:32:37', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('82b52c6fab80d28bdb65b0b00c100f24', '修改角色', '/role/update', '2021-01-11 13:56:08', '2021-01-11 13:56:08', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('9', '删除任务', '/task/delete', '2021-01-06 11:32:27', '2021-01-06 11:32:39', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('a04d17ba1b1c9c1197f2073959289bdd', '删除菜单', '/menu/delete', '2021-01-11 13:55:14', '2021-01-11 13:55:14', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('cff723434fb2e5d770c6b0268937f7d4', '分页列表', '/role/page', '2021-01-11 13:57:06', '2021-01-11 13:57:06', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);
INSERT INTO `sys_menu` VALUES ('e3def5aaa795e4017ac70b82fd70a8f9', '添加角色', '/role/save', '2021-01-11 13:55:59', '2021-01-11 13:55:59', 'data', NULL, 'wooho', '4e82ac897f0b266fc4c750f53e96441e', NULL);

-- ----------------------------
-- Table structure for sys_opera_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_opera_log`;
CREATE TABLE `sys_opera_log`  (
`id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`operator` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者',
`method_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名',
`class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类名',
`create_time` datetime(0) NOT NULL COMMENT '操作时间',
`visit_ip` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问者Ip',
`status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作状态：success fail',
`takeup_time` bigint(10) NULL DEFAULT NULL COMMENT '耗时',
`referer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问来源',
`user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者Id',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
`id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`role_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户角色',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
`user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户Id',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '用户管理', '2021-01-05 14:51:15', '2021-01-05 14:51:17', NULL);
INSERT INTO `sys_role` VALUES ('2', '调度管理', '2021-01-06 11:26:36', '2021-01-06 11:26:39', NULL);
INSERT INTO `sys_role` VALUES ('48f214c0bfbdc43b32a354cb497ef946', '菜单管理', '2021-01-11 13:59:21', '2021-01-11 13:59:21', NULL);
INSERT INTO `sys_role` VALUES ('5b85dd05b0777da3741f29bb964fa359', '角色管理', '2021-01-11 13:58:57', '2021-01-11 13:58:57', NULL);
INSERT INTO `sys_role` VALUES ('a9cbc84166c7480b9bdc9e45c841175b', '测试普通用户', '2021-02-03 16:06:40', '2021-02-03 16:06:40', '4e82ac897f0b266fc4c750f53e96441e');
INSERT INTO `sys_role` VALUES ('f48d0ad9a14401318f113e4d90afa3f8', '操作日志管理', '2021-01-06 18:43:32', '2021-01-06 19:07:35', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
`id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`role_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
`menu_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单Id',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('10', '2', '10');
INSERT INTO `sys_role_menu` VALUES ('11', '2', '11');
INSERT INTO `sys_role_menu` VALUES ('1ffbb4bf5b866ace233d79218bf0dbdb', 'a9cbc84166c7480b9bdc9e45c841175b', '11');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('2fc0f1f7b1f569215271ea9b1b77e0c8', '5b85dd05b0777da3741f29bb964fa359', '82b52c6fab80d28bdb65b0b00c100f24');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('4a5b7a56eee562c4911d195b1e5d81c1', '5b85dd05b0777da3741f29bb964fa359', '1e74f04e75c51fe5a00c8ee3308c5935');
INSERT INTO `sys_role_menu` VALUES ('4a84529f94ae8684f7d03ac02055d2bf', '5b85dd05b0777da3741f29bb964fa359', 'e3def5aaa795e4017ac70b82fd70a8f9');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('6c4dd934ce2761da37cfc7c910fa7daa', 'a9cbc84166c7480b9bdc9e45c841175b', '12');
INSERT INTO `sys_role_menu` VALUES ('6fa3fee662c8383a09075a0ffc67e929', '48f214c0bfbdc43b32a354cb497ef946', 'a04d17ba1b1c9c1197f2073959289bdd');
INSERT INTO `sys_role_menu` VALUES ('7', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('8', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('9', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('9044b5ff48307d61ab9fb039bc444f98', 'f48d0ad9a14401318f113e4d90afa3f8', '12');
INSERT INTO `sys_role_menu` VALUES ('939fa8b471e11e0e2473a839a3ea8741', '5b85dd05b0777da3741f29bb964fa359', 'cff723434fb2e5d770c6b0268937f7d4');
INSERT INTO `sys_role_menu` VALUES ('bd34a629ffa7aca3f4d2014d645e85da', '48f214c0bfbdc43b32a354cb497ef946', '19cc7658c3ad1bd6cfb657a201e18710');
INSERT INTO `sys_role_menu` VALUES ('c0f70c122e4894795838563b964bb7e0', '48f214c0bfbdc43b32a354cb497ef946', '3e320d07d905c8fe8b3d325eddacfd88');
INSERT INTO `sys_role_menu` VALUES ('c0f70c122e48963b9', '48f214c0bfbdc43b32a354cb497ef946', '31266da86090d900bd873276fb737b28');
INSERT INTO `sys_role_menu` VALUES ('eca2db934cb2f835f4a510898f68cc68', 'a9cbc84166c7480b9bdc9e45c841175b', '10');

-- ----------------------------
-- Table structure for sys_task_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_config`;
CREATE TABLE `sys_task_config`  (
`id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`task_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务全路径名',
`task_cron` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
`task_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务描述',
`task_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态：1 启用 0 禁用',
`task_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '定时任务名称',
`task_group` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务组',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
`user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户Id',
PRIMARY KEY (`id`, `task_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task_config
-- ----------------------------
INSERT INTO `sys_task_config` VALUES ('3', 'com.example.soo.task.ClearSysOperaJob', '0 */2 * * * ?', '每2分钟执行任务', '1', 'clearSysOpera', 'test', '2021-01-05 10:03:10', '2021-01-06 12:25:25', '4e82ac897f0b266fc4c750f53e96441e');
INSERT INTO `sys_task_config` VALUES ('8f252ef9b32b752f180618e1c868a023', 'com.example.soo.task.JobTwoDemo', '0 */1 * * * ?', '每1分钟执行任务', '1', 'two', 'test', '2021-01-06 12:09:33', '2021-01-06 17:25:43', '4e82ac897f0b266fc4c750f53e96441e');
INSERT INTO `sys_task_config` VALUES ('c695353bf0724d44efe7ddadcd48f0a5', 'com.example.soo.task.JobOneDemo', '*/30 * * * * ?', '每30秒执行任务', '1', 'one', 'test1', '2021-02-03 16:45:41', '2021-02-03 16:46:30', '4e82ac897f0b266fc4c750f53e96441e');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
`id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`user_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`pass_word` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`real_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`create_time` datetime(0) NOT NULL,
`super_admin` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '超级管理员 1：超级管理员',
`update_time` datetime(0) NULL DEFAULT NULL,
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('24f59b8614624e1e8b6b9cf8e5426edf', 'shenhaijin', 'shenhaijin', '沈海金', '2021-02-03 15:12:26', '0', '2021-02-03 15:12:26');
INSERT INTO `sys_user` VALUES ('4e82ac897f0b266fc4c750f53e96441e', 'admin', 'root', '超级管理员', '2021-01-08 11:34:12', '1', '2021-01-08 11:34:12');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
`id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`user_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户Id',
`role_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
