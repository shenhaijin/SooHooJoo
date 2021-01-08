/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 127.0.0.1:3306
 Source Schema         : servicegateway

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 08/01/2021 11:37:05
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
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `menu_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型 接口 页面',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '删除用户', '/user/delete', '2021-01-05 14:53:31', '2021-01-05 14:53:33', NULL);
INSERT INTO `sys_menu` VALUES ('10', '暂停任务', '/task/pause', '2021-01-06 11:32:30', '2021-01-06 11:32:41', NULL);
INSERT INTO `sys_menu` VALUES ('11', '启动任务', '/task/run', '2021-01-06 11:32:32', '2021-01-06 11:32:43', NULL);
INSERT INTO `sys_menu` VALUES ('12', '操作日志分页列表', '/sys/log/page', '2021-01-06 18:14:12', '2021-01-06 18:14:15', NULL);
INSERT INTO `sys_menu` VALUES ('2', '用户详情', '/user/detail', '2021-01-05 14:53:35', '2021-01-05 14:53:37', NULL);
INSERT INTO `sys_menu` VALUES ('3', '用户列表', '/user/list', '2021-01-05 14:53:39', '2021-01-05 14:53:41', NULL);
INSERT INTO `sys_menu` VALUES ('4', '用户分页列表', '/user/page', '2021-01-05 14:53:43', '2021-01-05 14:53:45', NULL);
INSERT INTO `sys_menu` VALUES ('5', '添加用户', '/user/save', '2021-01-05 14:53:47', '2021-01-05 14:53:49', NULL);
INSERT INTO `sys_menu` VALUES ('6', '分页列表', '/task/page', '2021-01-06 11:32:16', '2021-01-06 11:32:18', NULL);
INSERT INTO `sys_menu` VALUES ('7', '编辑任务', '/task/update', '2021-01-06 11:32:21', '2021-01-06 11:32:34', NULL);
INSERT INTO `sys_menu` VALUES ('8', '新增任务', '/task/save', '2021-01-06 11:32:25', '2021-01-06 11:32:37', NULL);
INSERT INTO `sys_menu` VALUES ('9', '删除任务', '/task/delete', '2021-01-06 11:32:27', '2021-01-06 11:32:39', NULL);

-- ----------------------------
-- Table structure for sys_opera_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_opera_log`;
CREATE TABLE `sys_opera_log`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operator` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `method_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名',
  `operation_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `visit_ip` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问者Ip',
  `operat_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作状态：success fail',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_opera_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户角色',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '用户管理', '2021-01-05 14:51:15', '2021-01-05 14:51:17');
INSERT INTO `sys_role` VALUES ('2', '调度管理', '2021-01-06 11:26:36', '2021-01-06 11:26:39');
INSERT INTO `sys_role` VALUES ('f48d0ad9a14401318f113e4d90afa3f8', '操作日志管理', '2021-01-06 18:43:32', '2021-01-06 19:07:35');

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
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('7', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('8', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('9', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('9044b5ff48307d61ab9fb039bc444f98', 'f48d0ad9a14401318f113e4d90afa3f8', '12');

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
  PRIMARY KEY (`id`, `task_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task_config
-- ----------------------------
INSERT INTO `sys_task_config` VALUES ('1', 'com.example.soo.task.JobOneDemo', '*/30 * * * * ?', '每30秒执行任务', '1', 'one', 'test', '2021-01-05 09:45:29', '2021-01-05 09:45:31');
INSERT INTO `sys_task_config` VALUES ('3', 'com.example.soo.task.ClearSysOperaJob', '0 */2 * * * ?', '每2分钟执行任务', '1', 'clearSysOpera', 'test', '2021-01-05 10:03:10', '2021-01-06 12:25:25');
INSERT INTO `sys_task_config` VALUES ('8f252ef9b32b752f180618e1c868a023', 'com.example.soo.task.JobTwoDemo', '0 */1 * * * ?', '每1分钟执行任务', '1', 'two', 'test', '2021-01-06 12:09:33', '2021-01-06 17:25:43');

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
