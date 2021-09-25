/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : prinsma-vue

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 21/01/2021 21:44:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('sysScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('sysScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 'com.example.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720018636F6D2E6578616D706C652E6D6F64656C2E5379734A6F62000000000000000102000D4C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000863726561746542797400104C6A6176612F6C616E672F4C6F6E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C0002696471007E000A4C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000672656D61726B71007E00094C000673746174757371007E00094C0008757064617465427971007E000A4C000A75706461746554696D6571007E000B787074000131707074000E302F3130202A202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740013746573745461736B2E6E6F506172616D73282974000744454641554C547400013174000131707400013170707800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('sysScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('sysScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('sysScheduler', 'WIN-0F44EC9N8331611140668134', 1611144828695, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `int_prop_1` int(11) NULL DEFAULT NULL,
  `int_prop_2` int(11) NULL DEFAULT NULL,
  `long_prop_1` bigint(20) NULL DEFAULT NULL,
  `long_prop_2` bigint(20) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(13) NULL DEFAULT NULL,
  `prev_fire_time` bigint(13) NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(2) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('sysScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 1611140670000, -1, 5, 'PAUSED', 'CRON', 1611140669000, 0, NULL, -1, '');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (16, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', '0', 1, '2020-12-31 09:40:28', NULL, NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (17, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', '0', 1, '2020-12-31 09:40:48', 1, '2020-12-31 09:41:22', '初始化密码 123456');
INSERT INTO `sys_config` VALUES (18, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', '0', 1, '2020-12-31 09:41:08', NULL, NULL, '深色主题theme-dark，浅色主题theme-light');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name_zh` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '部门名称(中文)',
  `dept_name_en` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '部门名称(英文)',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在1代表删除）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (8, 0, '0', 'IT', 'ITs', 1, 'Barry', '13724244614', '1009335882@qq.com', '0', '0', 1, '2020-12-21 13:58:28', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (9, 8, '0,8', '1', '1', 1, '1', '13724244614', '1@qq.com', '0', '0', 1, '2020-12-21 13:58:31', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (11, 8, '0,8', '3', '3', 3, '3', '13724244614', '3@qq.com', '0', '0', 1, '2020-12-21 13:58:38', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (12, 11, '0,8,11', '4', '4', 4, '4', '13724244614', '4@qq.com', '0', '0', 1, '2020-12-21 13:58:41', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (13, 9, '0,8,9', '2', '2', 2, '2', '13724244614', '2@qq.com', '0', '0', 1, '2020-12-21 13:58:34', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (14, 9, '0,8,9', '5', '5', 5, NULL, NULL, NULL, '0', '0', 1, '2020-12-18 11:20:34', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (15, 9, '0,8,9', '6', '6', 6, NULL, NULL, NULL, '0', '0', 1, '2020-12-18 11:20:44', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_user`;
CREATE TABLE `sys_dept_user`  (
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`user_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '部门与用户负责人关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_user
-- ----------------------------
INSERT INTO `sys_dept_user` VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_label_zh` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '字典标签(中文)',
  `dict_label_en` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '字典标签（英文）',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在1代表删除）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, '男', 'M', 1, '0', 'sys_user_sex', NULL, NULL, 'Y', '0', '0', 1, NULL, NULL, NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, '女', 'W', 2, '1', 'sys_user_sex', NULL, NULL, 'N', '0', '0', 1, NULL, NULL, NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, '未知', 'X', 3, '2', 'sys_user_sex', NULL, NULL, 'N', '0', '0', 1, NULL, NULL, NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, '正常', '正常', 1, '0', 'sys_normal_disable', NULL, 'primary', 'Y', '0', '0', 1, NULL, NULL, NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (5, '隐藏', '隐藏', 2, '1', 'sys_normal_disable', NULL, 'danger', 'N', '0', '0', 1, NULL, NULL, NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (6, '显示', '显示', 1, '0', 'sys_show_hide', NULL, NULL, 'N', '0', '0', 1, NULL, NULL, NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (7, '隐藏', '隐藏', 2, '1', 'sys_show_hide', NULL, NULL, 'N', '0', '0', 1, NULL, NULL, NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (10, '正常', '正常', 1, '0', 'sys_job_status', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 16:45:16', NULL, NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (11, '暂停', '暂停', 2, '1', 'sys_job_status', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 16:45:41', NULL, NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (12, '默认', '默认', 1, 'DEFAULT', 'sys_job_group', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 16:49:52', NULL, NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (13, '系统', '系统', 2, 'SYSTEM', 'sys_job_group', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 16:50:28', NULL, NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (14, '是', '是', 1, 'Y', 'sys_yes_no', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 16:52:54', NULL, NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (15, '否', '否', 2, 'N', 'sys_yes_no', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 16:53:11', NULL, NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (16, '通知', '通知', 1, '1', 'sys_notice_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:05:00', NULL, NULL, '');
INSERT INTO `sys_dict_data` VALUES (17, '公告', '公告', 2, '2', 'sys_notice_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:05:13', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (18, '新增', '新增', 1, '1', 'sys_oper_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:07:40', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (19, '修改', '修改', 2, '2', 'sys_oper_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:07:52', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (20, '删除', '删除', 3, '3', 'sys_oper_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:08:00', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (21, '授权', '授权', 4, '4', 'sys_oper_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:08:12', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (22, '导出', '导出', 5, '5', 'sys_oper_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:08:22', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (23, '导入', '导入', 6, '6', 'sys_oper_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:08:31', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (24, '强退', '强退', 7, '7', 'sys_oper_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:08:40', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (25, '生成代码', '生成代码', 8, '8', 'sys_oper_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:08:48', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (26, '清空数据', '清空数据', 9, '9', 'sys_oper_type', NULL, NULL, 'N', '0', '0', 1, '2020-12-01 17:08:58', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (31, '正常', '正常', 1, '0', 'sys_notice_status', NULL, NULL, 'N', '0', '0', 1, '2020-12-31 11:34:59', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (32, '关闭', '关闭', 2, '1', 'sys_notice_status', NULL, NULL, 'N', '0', '0', 1, '2020-12-31 11:35:15', NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (33, '成功', '成功', 1, '0', 'sys_common_status', NULL, NULL, 'N', '0', '0', 1, '2020-12-31 22:12:29', NULL, NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (34, '失败', '失败', 2, '1', 'sys_common_status', NULL, NULL, 'N', '0', '0', 1, '2020-12-31 22:12:52', NULL, NULL, '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在1代表删除）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', '0', NULL, NULL, 1, '2020-12-07 09:43:12', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', '0', 1, '2020-11-29 23:25:31', 1, '2020-12-07 09:43:15', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', '0', 1, '2020-11-29 23:37:06', 1, '2020-12-07 09:43:18', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', '0', 1, '2020-11-29 23:37:19', 1, '2020-12-07 09:43:21', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', '0', 1, '2020-11-29 23:37:33', 1, '2020-12-07 09:43:27', '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', '0', 1, '2020-11-29 23:37:46', 1, '2020-12-07 09:43:34', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', '0', 1, '2020-11-29 23:38:01', 1, '2020-12-07 09:43:31', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', '0', 1, '2020-11-29 23:38:15', 1, '2020-12-07 09:43:37', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', '0', 1, '2020-11-29 23:38:28', 1, '2020-12-07 09:43:40', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', '0', 1, '2020-11-29 23:38:53', 1, '2020-12-07 09:43:43', '登录状态列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '1', 'DEFAULT', 'testTask.noParams()', '0/10 * * * * ?', '1', '1', '1', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
INSERT INTO `sys_job_log` VALUES (1, '1', 'DEFAULT', 'testTask.noParams()', '1 总共耗时：1毫秒', '0', '', NULL);
INSERT INTO `sys_job_log` VALUES (2, '1', 'DEFAULT', 'testTask.noParams()', '1 总共耗时：0毫秒', '0', '', NULL);

-- ----------------------------
-- Table structure for sys_login_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_info`;
CREATE TABLE `sys_login_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 194 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_info
-- ----------------------------
INSERT INTO `sys_login_info` VALUES (1, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (2, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (3, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (4, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (5, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (6, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (7, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (8, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (9, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (10, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (11, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (12, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (13, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (14, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (15, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (16, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (17, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (18, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (19, 'admin1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (20, 'admin1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (21, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (22, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (23, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (24, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (25, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (26, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (27, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (28, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (29, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (30, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (31, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (32, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (33, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (34, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (35, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (36, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (37, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (38, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (39, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (40, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (41, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (42, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (43, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (44, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (45, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (46, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (47, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (48, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (49, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (50, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (51, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (52, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (53, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (54, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (55, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (56, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (57, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (58, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (59, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (60, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (61, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (62, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (63, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (64, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (65, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (66, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (67, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (68, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (69, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (70, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (71, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (72, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (73, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (74, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (75, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (76, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (77, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (78, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (79, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (80, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (81, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (82, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (83, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (84, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (85, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (86, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (87, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (88, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (89, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', NULL);
INSERT INTO `sys_login_info` VALUES (90, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (91, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (92, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (93, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (94, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (95, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (96, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (97, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (98, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (99, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (110, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (111, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (112, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (113, 'ruoyi', '127.0.0.1', '内网IP', 'Microsoft Edge', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (114, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (115, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (116, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (117, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (118, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (119, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (120, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (121, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (122, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (123, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (124, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (125, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (126, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (127, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (128, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (129, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (130, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (131, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (132, 'admin1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (133, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (134, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (135, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (136, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', NULL);
INSERT INTO `sys_login_info` VALUES (137, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (138, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (139, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (140, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', NULL);
INSERT INTO `sys_login_info` VALUES (141, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (142, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (143, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (144, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (145, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码已失效', NULL);
INSERT INTO `sys_login_info` VALUES (146, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (147, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (148, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (149, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (150, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (151, 'admin12', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (152, 'admin12', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (153, 'admin12', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (154, 'admin12', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (155, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (156, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (157, 'admin1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (158, 'admin1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (159, 'admin1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (160, 'admin1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (161, 'admin1', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (162, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (163, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (164, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (165, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (166, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (167, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (168, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (169, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (170, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (171, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (172, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (173, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (174, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '用户不存在/密码错误', NULL);
INSERT INTO `sys_login_info` VALUES (175, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (176, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (177, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '1', '验证码错误', NULL);
INSERT INTO `sys_login_info` VALUES (178, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (179, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (180, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (181, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (182, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (183, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (184, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (185, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (186, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (187, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (188, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (189, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (190, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (191, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);
INSERT INTO `sys_login_info` VALUES (192, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', NULL);
INSERT INTO `sys_login_info` VALUES (193, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name_zh` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单名称(中文)',
  `menu_name_en` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单名称(英文)',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 180 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', '系统管理', 0, 1, 'system', NULL, 1, 0, 'M', '0', NULL, 'system', '0', NULL, NULL, 1, '2020-12-07 10:11:16', NULL);
INSERT INTO `sys_menu` VALUES (2, '系统监控', '系统监控', 0, 2, 'monitor', NULL, 1, 0, 'M', '0', NULL, 'monitor', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, '系统工具', '系统工具', 0, 3, 'tool', NULL, 1, 0, 'M', '0', NULL, 'tool', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (100, '用户管理', '用户管理', 1, 1, 'user', 'system/user/index', 1, 0, 'C', '0', 'system:user:list', 'user', '0', NULL, NULL, 1, '2020-12-07 10:11:50', NULL);
INSERT INTO `sys_menu` VALUES (101, '角色管理', '角色管理', 1, 2, 'role', 'system/role/index', 1, 0, 'C', '0', 'system:role:list', 'peoples', '0', NULL, NULL, 1, '2020-12-07 10:13:02', NULL);
INSERT INTO `sys_menu` VALUES (102, '菜单管理', '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 0, 'C', '0', 'system:menu:list', 'tree-table', '0', NULL, NULL, 1, '2020-12-07 10:13:25', NULL);
INSERT INTO `sys_menu` VALUES (103, '部门管理', '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 0, 'C', '0', 'system:dept:list', 'tree', '0', NULL, NULL, 1, '2020-12-07 10:13:44', NULL);
INSERT INTO `sys_menu` VALUES (104, '岗位管理', '岗位管理', 1, 5, 'post', 'system/post/index', 1, 0, 'C', '0', 'system:post:list', 'post', '0', NULL, NULL, 1, '2020-12-07 10:14:14', NULL);
INSERT INTO `sys_menu` VALUES (105, '字典管理', '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 0, 'C', '0', 'system:dict:list', 'dict', '0', NULL, NULL, 1, '2020-12-07 10:14:38', NULL);
INSERT INTO `sys_menu` VALUES (106, '参数设置', '参数设置', 1, 7, 'config', 'system/config/index', 1, 0, 'C', '0', 'system:config:list', 'edit', '0', NULL, NULL, 1, '2020-12-07 10:14:50', NULL);
INSERT INTO `sys_menu` VALUES (107, '通知公告', '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 0, 'C', '0', 'system:notice:list', 'message', '0', NULL, NULL, 1, '2020-12-07 10:15:03', NULL);
INSERT INTO `sys_menu` VALUES (113, '日志管理', '日志管理', 1, 9, 'log', NULL, 1, 0, 'M', '0', NULL, 'log', '0', 1, '2020-12-07 10:24:59', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (114, '用户查询', '用户查询', 100, 1, '', NULL, 1, 0, 'F', '0', 'system:user:query', '#', '0', 1, '2020-12-07 10:25:33', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (115, '用户新增', '用户新增', 100, 2, '', NULL, 1, 0, 'F', '0', 'system:user:add', '#', '0', 1, '2020-12-07 10:27:38', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (116, '用户修改', '用户修改', 100, 3, '', NULL, 1, 0, 'F', '0', 'system:user:edit', '#', '0', 1, '2020-12-07 10:27:55', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (117, '用户删除', '用户删除', 100, 4, '', NULL, 1, 0, 'F', '0', 'system:user:remove', '#', '0', 1, '2020-12-07 10:28:22', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (118, '用户导出', '用户导出', 100, 5, '', NULL, 1, 0, 'F', '0', 'system:user:export', '#', '0', 1, '2020-12-07 10:29:16', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (119, '用户导入', '用户导入', 100, 6, '', NULL, 1, 0, 'F', '0', 'system:user:import', '#', '0', 1, '2020-12-07 10:29:34', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (120, '重置密码', '重置密码', 100, 7, '', NULL, 1, 0, 'F', '0', 'system:user:resetPwd', '#', '0', 1, '2020-12-07 10:43:25', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (121, '角色查询', '角色查询', 101, 1, '', NULL, 1, 1, 'F', '0', 'system:role:query', '#', '0', 1, '2020-12-07 10:57:33', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (122, '角色新增', '角色新增', 101, 2, '', NULL, 1, 1, 'F', '0', 'system:role:add', '#', '0', 1, '2020-12-07 10:58:11', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (123, '角色修改', '角色修改', 101, 3, '', NULL, 1, 1, 'F', '0', 'system:role:edit', '#', '0', 1, '2020-12-07 10:58:26', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (124, '角色删除', '角色删除', 101, 4, '', NULL, 1, 1, 'F', '0', 'system:role:remove', '#', '0', 1, '2020-12-07 10:58:41', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (125, '角色导出', '角色导出', 101, 5, '', NULL, 1, 1, 'F', '0', 'system:role:export', '#', '0', 1, '2020-12-07 10:58:58', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (126, '菜单查询', '菜单查询', 102, 1, '', NULL, 1, 1, 'F', '0', 'system:menu:query', '#', '0', 1, '2020-12-07 11:33:22', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (127, '菜单新增', '菜单新增', 102, 2, '', NULL, 1, 1, 'F', '0', '	 system:menu:add', '#', '0', 1, '2020-12-07 11:33:43', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (128, '菜单修改', '菜单修改', 102, 3, '', NULL, 1, 1, 'F', '0', 'system:menu:edit', '#', '0', 1, '2020-12-07 11:33:56', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (130, '菜单删除', '菜单删除', 102, 4, '', NULL, 1, 1, 'F', '0', 'system:menu:remove', '#', '0', 1, '2020-12-07 11:35:03', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (131, '部门查询', '部门查询', 103, 1, '', NULL, 1, 1, 'F', '0', 'system:dept:query', '#', '0', 1, '2020-12-07 11:35:28', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (132, '部门新增', '部门新增', 103, 2, '', NULL, 1, 1, 'F', '0', 'system:dept:add', '#', '0', 1, '2020-12-07 11:35:44', 1, '2020-12-07 11:35:53', NULL);
INSERT INTO `sys_menu` VALUES (133, '部门修改', '部门修改', 103, 3, '', NULL, 1, 1, 'F', '0', 'system:dept:edit', '#', '0', 1, '2020-12-07 11:36:14', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (134, '部门删除', '部门删除', 103, 4, '', NULL, 1, 1, 'F', '0', 'system:dept:remove', '#', '0', 1, '2020-12-07 11:36:33', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (135, '岗位查询', '岗位查询', 104, 1, '', NULL, 1, 1, 'F', '0', 'system:post:query', '#', '0', 1, '2020-12-07 11:37:08', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (136, '岗位新增', '岗位新增', 104, 2, '', NULL, 1, 1, 'F', '0', 'system:post:add', '#', '0', 1, '2020-12-07 11:37:21', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (137, '岗位修改', '岗位修改', 104, 3, '', NULL, 1, 1, 'F', '0', 'system:post:edit', '#', '0', 1, '2020-12-07 11:37:34', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (138, '岗位删除', '岗位删除', 104, 4, '', NULL, 1, 1, 'F', '0', 'system:post:remove', '#', '0', 1, '2020-12-07 11:37:51', 1, '2020-12-07 11:38:12', NULL);
INSERT INTO `sys_menu` VALUES (139, '岗位导出', '岗位导出', 104, 5, '', NULL, 1, 1, 'F', '0', 'system:post:export', '#', '0', 1, '2020-12-07 11:38:07', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (140, '字典查询', '字典查询', 105, 1, '', NULL, 1, 1, 'F', '0', 'system:dict:query', '#', '0', 1, '2020-12-07 11:38:37', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (141, '字典新增', '字典新增', 105, 2, '', NULL, 1, 1, 'F', '0', 'system:dict:add', '#', '0', 1, '2020-12-07 11:38:58', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (142, '字典修改', '字典修改', 105, 3, '', NULL, 1, 1, 'F', '0', 'system:dict:edit', '#', '0', 1, '2020-12-07 11:39:14', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (143, '字典删除', '字典删除', 105, 4, '', NULL, 1, 1, 'F', '0', 'system:dict:remove', '#', '0', 1, '2020-12-07 11:39:26', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (144, '字典导出', '字典导出', 105, 5, '', NULL, 1, 1, 'F', '0', 'system:dict:export', '#', '0', 1, '2020-12-07 11:39:40', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (146, '参数查询', '参数查询', 106, 1, '', NULL, 1, 1, 'F', '0', 'system:config:query', '#', '0', 1, '2020-12-28 11:15:27', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (147, '参数新增', '参数新增', 106, 2, '', NULL, 1, 1, 'F', '0', 'system:config:add', '#', '0', 1, '2020-12-28 11:15:45', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (148, '参数修改', '参数修改', 106, 3, '', NULL, 1, 1, 'F', '0', 'system:config:edit', '#', '0', 1, '2020-12-28 11:16:08', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (149, '参数删除', '参数删除', 106, 4, '', NULL, 1, 1, 'F', '0', 'system:config:remove', '#', '0', 1, '2020-12-28 11:16:26', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (150, '参数导出', '参数导出', 106, 5, '', NULL, 1, 1, 'F', '0', 'system:config:export', '#', '0', 1, '2020-12-28 11:16:45', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (151, '公告查询', '公告查询', 107, 1, '', NULL, 1, 1, 'F', '0', 'system:notice:query', '#', '0', 1, '2020-12-28 11:17:10', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (152, '公告新增', '公告新增', 107, 2, '', NULL, 1, 1, 'F', '0', 'system:notice:add', '#', '0', 1, '2020-12-28 11:17:32', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (153, '公告修改', '公告修改', 107, 3, '', NULL, 1, 1, 'F', '0', 'system:notice:edit', '#', '0', 1, '2020-12-28 11:17:49', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (154, '公告删除', '公告删除', 107, 4, '', NULL, 1, 1, 'F', '0', 'system:notice:remove', '#', '0', 1, '2020-12-28 11:18:08', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (155, '操作日志', '操作日志', 113, 1, 'operlog', 'monitor/operlog/index', 1, 0, 'C', '0', 'monitor:operlog:list', 'component', '0', 1, '2020-12-28 11:26:55', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (156, '登录日志', '登录日志', 113, 2, 'logininfor', 'monitor/logininfor/index', 1, 0, 'C', '0', 'monitor/logininfor/index', 'form', '0', 1, '2020-12-28 11:31:38', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (157, '操作查询', '操作查询', 155, 1, '', NULL, 1, 1, 'F', '0', 'monitor:operlog:query', '#', '0', 1, '2020-12-28 11:33:40', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (158, '操作删除', '操作删除', 155, 2, '', NULL, 1, 1, 'F', '0', 'monitor:operlog:remove', '#', '0', 1, '2020-12-28 11:34:03', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (159, '日志导出', '日志导出', 155, 3, '', NULL, 1, 1, 'F', '0', 'monitor:operlog:export', '#', '0', 1, '2020-12-28 11:38:07', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (160, '登录查询', '登录查询', 156, 1, '', NULL, 1, 1, 'F', '0', 'monitor:logininfor:query', '#', '0', 1, '2020-12-28 11:38:31', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (161, '登录删除', '登录删除', 156, 2, '', NULL, 1, 1, 'F', '0', 'monitor:logininfor:remove', '#', '0', 1, '2020-12-28 11:38:53', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (162, '日志导出', '日志导出', 156, 3, '', NULL, 1, 1, 'F', '0', 'monitor:logininfor:export', '#', '0', 1, '2020-12-28 11:39:08', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (163, '在线用户', '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 0, 'C', '0', 'monitor:online:list', 'peoples', '0', 1, '2020-12-28 11:43:48', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (164, '在线查询', '在线查询', 163, 1, '', NULL, 1, 1, 'F', '0', 'monitor:online:query', '#', '0', 1, '2020-12-28 11:47:04', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (165, '批量强退', '批量强退', 163, 2, '', NULL, 1, 1, 'F', '0', 'monitor:online:batchLogout', '#', '0', 1, '2020-12-28 11:47:22', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (166, '单条强退', '单条强退', 163, 3, '', NULL, 1, 1, 'F', '0', 'monitor:online:forceLogout', '#', '0', 1, '2020-12-28 11:47:42', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (167, '定时任务', '定时任务', 2, 2, 'job', 'monitor/job/index', 1, 0, 'C', '0', 'monitor:job:list', 'clipboard', '0', 1, '2020-12-28 11:48:18', 1, '2020-12-31 21:55:23', NULL);
INSERT INTO `sys_menu` VALUES (168, '任务查询', '任务查询', 167, 1, '', NULL, 1, 1, 'F', '0', 'monitor:job:query', '#', '0', 1, '2020-12-28 11:50:17', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (169, '任务新增', '任务新增', 167, 2, '', NULL, 1, 1, 'F', '0', 'monitor:job:add', '#', '0', 1, '2020-12-28 11:50:36', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (170, '任务修改', '任务修改', 167, 3, '', NULL, 1, 1, 'F', '0', 'monitor:job:edit', '#', '0', 1, '2020-12-28 11:50:53', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (171, '任务删除', '任务删除', 167, 4, '', NULL, 1, 1, 'F', '0', 'monitor:job:remove', '#', '0', 1, '2020-12-28 11:51:11', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (172, '状态修改', '状态修改', 167, 5, '', NULL, 1, 1, 'F', '0', 'monitor:job:changeStatus', '#', '0', 1, '2020-12-28 11:51:37', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (173, '任务导出', '任务导出', 167, 6, '', NULL, 1, 1, 'F', '0', 'monitor:job:export', '#', '0', 1, '2020-12-28 11:51:55', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (174, '数据监控', '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 0, 'C', '0', 'monitor:druid:list', 'dashboard', '0', 1, '2020-12-28 11:52:33', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (175, '服务监控', '服务监控', 2, 4, 'server', 'monitor/server/index', 1, 0, 'C', '0', 'monitor:server:list', 'server', '0', 1, '2020-12-28 11:53:10', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (176, '缓存监控', '缓存监控', 2, 5, 'cache', 'monitor/cache/index', 1, 0, 'C', '0', 'monitor:cache:list', 'fullscreen', '0', 1, '2020-12-28 11:53:49', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (177, '表单构建', '表单构建', 3, 1, 'build', 'tool/build/index', 1, 0, 'C', '0', 'tool:build:list', 'build', '0', 1, '2020-12-28 11:54:52', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (178, '系统接口', '系统接口', 3, 3, 'swagger', 'tool/swagger/index', 1, 0, 'C', '0', 'tool:swagger:list', 'swagger', '0', 1, '2020-12-28 11:55:32', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (179, '代码生成', '代码生成', 3, 2, 'gen', 'tool/gen/index', 1, 0, 'C', '0', 'tool:gen:list', 'code', '0', 1, '2020-12-28 11:56:07', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在1代表删除）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (9, '1', '1', 0x3C703E3132333C2F703E, '0', '0', 1, '2020-12-31 15:51:18', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '岗位编码',
  `post_name_zh` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '岗位名称(中文)',
  `post_name_en` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '岗位名称(英文)',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `dept_name_zh` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '部门名称(中文)',
  `dept_name_en` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '部门名称(英文)',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0存1删）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0存1删）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (3, 'it', 'it', 'it', 8, 'IT', 'ITs', 1, '0', '0', 1, '2020-12-04 15:06:25', 1, '2020-12-21 13:58:50', NULL);
INSERT INTO `sys_post` VALUES (5, '123', '123', '123', 13, '2', '2', 1, '0', '0', 1, '2020-12-04 15:20:12', 1, '2020-12-18 17:25:08', NULL);
INSERT INTO `sys_post` VALUES (6, '1', '12314', '1234', 15, '6', '6', 1, '0', '0', 1, '2020-12-18 17:22:54', NULL, NULL, NULL);
INSERT INTO `sys_post` VALUES (7, '3', '3', '3', 11, '3', '3', 1, '0', '0', 1, '2020-12-18 17:23:05', NULL, NULL, '3');
INSERT INTO `sys_post` VALUES (8, '5', '5', '5', 14, '5', '5', 1, '0', '0', 1, '2020-12-18 17:25:18', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色编号',
  `role_name_zh` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称(中文)',
  `role_name_en` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称(英文)',
  `order_num` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0存1删）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0存1删）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'it', 'IT管理员', 'CTO', 1, '0', '1', NULL, NULL, 1, '2020-12-31 21:55:54', NULL);
INSERT INTO `sys_role` VALUES (3, NULL, '老板', '老板', 2, '0', '0', 1, '2020-12-17 14:24:44', 1, '2020-12-31 09:38:34', '黄锦涛');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 100);
INSERT INTO `sys_role_menu` VALUES (1, 101);
INSERT INTO `sys_role_menu` VALUES (1, 102);
INSERT INTO `sys_role_menu` VALUES (1, 103);
INSERT INTO `sys_role_menu` VALUES (1, 104);
INSERT INTO `sys_role_menu` VALUES (1, 105);
INSERT INTO `sys_role_menu` VALUES (1, 106);
INSERT INTO `sys_role_menu` VALUES (1, 107);
INSERT INTO `sys_role_menu` VALUES (1, 113);
INSERT INTO `sys_role_menu` VALUES (1, 114);
INSERT INTO `sys_role_menu` VALUES (1, 115);
INSERT INTO `sys_role_menu` VALUES (1, 116);
INSERT INTO `sys_role_menu` VALUES (1, 117);
INSERT INTO `sys_role_menu` VALUES (1, 118);
INSERT INTO `sys_role_menu` VALUES (1, 119);
INSERT INTO `sys_role_menu` VALUES (1, 120);
INSERT INTO `sys_role_menu` VALUES (1, 121);
INSERT INTO `sys_role_menu` VALUES (1, 122);
INSERT INTO `sys_role_menu` VALUES (1, 123);
INSERT INTO `sys_role_menu` VALUES (1, 124);
INSERT INTO `sys_role_menu` VALUES (1, 125);
INSERT INTO `sys_role_menu` VALUES (1, 126);
INSERT INTO `sys_role_menu` VALUES (1, 127);
INSERT INTO `sys_role_menu` VALUES (1, 128);
INSERT INTO `sys_role_menu` VALUES (1, 130);
INSERT INTO `sys_role_menu` VALUES (1, 131);
INSERT INTO `sys_role_menu` VALUES (1, 132);
INSERT INTO `sys_role_menu` VALUES (1, 133);
INSERT INTO `sys_role_menu` VALUES (1, 134);
INSERT INTO `sys_role_menu` VALUES (1, 135);
INSERT INTO `sys_role_menu` VALUES (1, 136);
INSERT INTO `sys_role_menu` VALUES (1, 137);
INSERT INTO `sys_role_menu` VALUES (1, 138);
INSERT INTO `sys_role_menu` VALUES (1, 139);
INSERT INTO `sys_role_menu` VALUES (1, 140);
INSERT INTO `sys_role_menu` VALUES (1, 141);
INSERT INTO `sys_role_menu` VALUES (1, 142);
INSERT INTO `sys_role_menu` VALUES (1, 143);
INSERT INTO `sys_role_menu` VALUES (1, 144);
INSERT INTO `sys_role_menu` VALUES (1, 146);
INSERT INTO `sys_role_menu` VALUES (1, 147);
INSERT INTO `sys_role_menu` VALUES (1, 148);
INSERT INTO `sys_role_menu` VALUES (1, 149);
INSERT INTO `sys_role_menu` VALUES (1, 150);
INSERT INTO `sys_role_menu` VALUES (1, 151);
INSERT INTO `sys_role_menu` VALUES (1, 152);
INSERT INTO `sys_role_menu` VALUES (1, 153);
INSERT INTO `sys_role_menu` VALUES (1, 154);
INSERT INTO `sys_role_menu` VALUES (1, 155);
INSERT INTO `sys_role_menu` VALUES (1, 156);
INSERT INTO `sys_role_menu` VALUES (1, 157);
INSERT INTO `sys_role_menu` VALUES (1, 158);
INSERT INTO `sys_role_menu` VALUES (1, 159);
INSERT INTO `sys_role_menu` VALUES (1, 160);
INSERT INTO `sys_role_menu` VALUES (1, 161);
INSERT INTO `sys_role_menu` VALUES (1, 162);
INSERT INTO `sys_role_menu` VALUES (1, 163);
INSERT INTO `sys_role_menu` VALUES (1, 164);
INSERT INTO `sys_role_menu` VALUES (1, 165);
INSERT INTO `sys_role_menu` VALUES (1, 166);
INSERT INTO `sys_role_menu` VALUES (1, 167);
INSERT INTO `sys_role_menu` VALUES (1, 168);
INSERT INTO `sys_role_menu` VALUES (1, 169);
INSERT INTO `sys_role_menu` VALUES (1, 170);
INSERT INTO `sys_role_menu` VALUES (1, 171);
INSERT INTO `sys_role_menu` VALUES (1, 172);
INSERT INTO `sys_role_menu` VALUES (1, 173);
INSERT INTO `sys_role_menu` VALUES (1, 174);
INSERT INTO `sys_role_menu` VALUES (1, 175);
INSERT INTO `sys_role_menu` VALUES (1, 176);
INSERT INTO `sys_role_menu` VALUES (1, 177);
INSERT INTO `sys_role_menu` VALUES (1, 178);
INSERT INTO `sys_role_menu` VALUES (1, 179);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (3, 100);
INSERT INTO `sys_role_menu` VALUES (3, 101);
INSERT INTO `sys_role_menu` VALUES (3, 102);
INSERT INTO `sys_role_menu` VALUES (3, 103);
INSERT INTO `sys_role_menu` VALUES (3, 104);
INSERT INTO `sys_role_menu` VALUES (3, 105);
INSERT INTO `sys_role_menu` VALUES (3, 106);
INSERT INTO `sys_role_menu` VALUES (3, 107);
INSERT INTO `sys_role_menu` VALUES (3, 113);
INSERT INTO `sys_role_menu` VALUES (3, 114);
INSERT INTO `sys_role_menu` VALUES (3, 115);
INSERT INTO `sys_role_menu` VALUES (3, 116);
INSERT INTO `sys_role_menu` VALUES (3, 117);
INSERT INTO `sys_role_menu` VALUES (3, 118);
INSERT INTO `sys_role_menu` VALUES (3, 119);
INSERT INTO `sys_role_menu` VALUES (3, 120);
INSERT INTO `sys_role_menu` VALUES (3, 121);
INSERT INTO `sys_role_menu` VALUES (3, 122);
INSERT INTO `sys_role_menu` VALUES (3, 123);
INSERT INTO `sys_role_menu` VALUES (3, 124);
INSERT INTO `sys_role_menu` VALUES (3, 125);
INSERT INTO `sys_role_menu` VALUES (3, 126);
INSERT INTO `sys_role_menu` VALUES (3, 127);
INSERT INTO `sys_role_menu` VALUES (3, 128);
INSERT INTO `sys_role_menu` VALUES (3, 130);
INSERT INTO `sys_role_menu` VALUES (3, 131);
INSERT INTO `sys_role_menu` VALUES (3, 132);
INSERT INTO `sys_role_menu` VALUES (3, 133);
INSERT INTO `sys_role_menu` VALUES (3, 134);
INSERT INTO `sys_role_menu` VALUES (3, 135);
INSERT INTO `sys_role_menu` VALUES (3, 136);
INSERT INTO `sys_role_menu` VALUES (3, 137);
INSERT INTO `sys_role_menu` VALUES (3, 138);
INSERT INTO `sys_role_menu` VALUES (3, 139);
INSERT INTO `sys_role_menu` VALUES (3, 140);
INSERT INTO `sys_role_menu` VALUES (3, 141);
INSERT INTO `sys_role_menu` VALUES (3, 142);
INSERT INTO `sys_role_menu` VALUES (3, 143);
INSERT INTO `sys_role_menu` VALUES (3, 144);
INSERT INTO `sys_role_menu` VALUES (3, 146);
INSERT INTO `sys_role_menu` VALUES (3, 147);
INSERT INTO `sys_role_menu` VALUES (3, 148);
INSERT INTO `sys_role_menu` VALUES (3, 149);
INSERT INTO `sys_role_menu` VALUES (3, 150);
INSERT INTO `sys_role_menu` VALUES (3, 151);
INSERT INTO `sys_role_menu` VALUES (3, 152);
INSERT INTO `sys_role_menu` VALUES (3, 153);
INSERT INTO `sys_role_menu` VALUES (3, 154);
INSERT INTO `sys_role_menu` VALUES (3, 155);
INSERT INTO `sys_role_menu` VALUES (3, 156);
INSERT INTO `sys_role_menu` VALUES (3, 157);
INSERT INTO `sys_role_menu` VALUES (3, 158);
INSERT INTO `sys_role_menu` VALUES (3, 159);
INSERT INTO `sys_role_menu` VALUES (3, 160);
INSERT INTO `sys_role_menu` VALUES (3, 161);
INSERT INTO `sys_role_menu` VALUES (3, 162);
INSERT INTO `sys_role_menu` VALUES (3, 163);
INSERT INTO `sys_role_menu` VALUES (3, 164);
INSERT INTO `sys_role_menu` VALUES (3, 165);
INSERT INTO `sys_role_menu` VALUES (3, 166);
INSERT INTO `sys_role_menu` VALUES (3, 167);
INSERT INTO `sys_role_menu` VALUES (3, 168);
INSERT INTO `sys_role_menu` VALUES (3, 169);
INSERT INTO `sys_role_menu` VALUES (3, 170);
INSERT INTO `sys_role_menu` VALUES (3, 171);
INSERT INTO `sys_role_menu` VALUES (3, 172);
INSERT INTO `sys_role_menu` VALUES (3, 173);
INSERT INTO `sys_role_menu` VALUES (3, 174);
INSERT INTO `sys_role_menu` VALUES (3, 175);
INSERT INTO `sys_role_menu` VALUES (3, 176);
INSERT INTO `sys_role_menu` VALUES (3, 177);
INSERT INTO `sys_role_menu` VALUES (3, 178);
INSERT INTO `sys_role_menu` VALUES (3, 179);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上一级负责人ID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '密码',
  `nick_name_zh` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '中文名',
  `nick_name_en` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '英文名',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '用户类型',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '头像地址',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0存1删）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0存1删）',
  `theme` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT 'variables.theme' COMMENT '主题',
  `is_tab` tinyint(4) NULL DEFAULT 1 COMMENT '是否开启tab，0关闭 1开启',
  `is_set` tinyint(4) NULL DEFAULT 0 COMMENT '是否系统布局配置，0关闭 1开启',
  `is_header` tinyint(4) NULL DEFAULT 0 COMMENT '是否固定头部，0关闭 1开启',
  `is_logo` tinyint(4) NULL DEFAULT 1 COMMENT '是否显示logo，0关闭 1开启',
  `login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, NULL, 'admin', '$2a$10$RWdIyOyAQxTvTFRvEVWzQOHEaYvAtDsGZ7POYmvgw1t3gOBZ.SS32', '黄锦涛', 'barry', '', '1009335882@qq.com', '13724244614', '0', '/profile/avatar/2021/01/05/724c7b7c-1723-469e-bd76-bbf86e571857.jpeg', '0', '0', 'variables.theme', 1, 0, 0, 1, '', NULL, NULL, '2020-11-28 21:35:31', 1, '2021-01-05 20:48:11', NULL);
INSERT INTO `sys_user` VALUES (10, NULL, 'admin1', '$2a$10$RWdIyOyAQxTvTFRvEVWzQOHEaYvAtDsGZ7POYmvgw1t3gOBZ.SS32', '许欢攀', 'Xu', '', '1@qq.com', '13724244613', '0', '', '0', '0', 'variables.theme', 1, 0, 0, 1, '', NULL, NULL, '2020-12-21 17:03:40', 1, '2020-12-28 11:04:35', '1');
INSERT INTO `sys_user` VALUES (12, NULL, 'admin12', '$2a$10$R2xyyMJ.VcRzwlTNw9qETOgbzHUCZ7QjEa/4jHpPHVns6O6fwuxJO', '', '', '', '12345@a.com', '13724244615', '0', '', '0', '0', 'variables.theme', 1, 0, 0, 1, '', NULL, NULL, '2020-12-31 09:36:38', 1, '2021-01-04 21:02:43', NULL);

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`user_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '用户与部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------
INSERT INTO `sys_user_dept` VALUES (1, 8);
INSERT INTO `sys_user_dept` VALUES (10, 8);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 3);
INSERT INTO `sys_user_post` VALUES (1, 5);
INSERT INTO `sys_user_post` VALUES (1, 6);
INSERT INTO `sys_user_post` VALUES (10, 3);
INSERT INTO `sys_user_post` VALUES (10, 5);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 3);
INSERT INTO `sys_user_role` VALUES (10, 1);

SET FOREIGN_KEY_CHECKS = 1;
