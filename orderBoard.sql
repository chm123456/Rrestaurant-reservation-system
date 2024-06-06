

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `orderboard`;
CREATE DATABASE `orderboard`;
use `orderboard`;

-- ----------------------------
-- Table structure for boardmanage
-- ----------------------------
DROP TABLE IF EXISTS `boardmanage`;
CREATE TABLE `boardmanage`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `boardcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'boardCode',
  `boardname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'boardName',
  `boardtype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'boardType',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'table_BoardManage' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boardmanage
-- ----------------------------
INSERT INTO `boardmanage` VALUES (1, '001', '仁和厅', '4人桌');

-- ----------------------------
-- Table structure for boardorder
-- ----------------------------
DROP TABLE IF EXISTS `boardorder`;
CREATE TABLE `boardorder`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'phone',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'time',
  `numofdiners` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'numOfDiners',
  `userId` int(10) NULL DEFAULT NULL COMMENT 'userID',
  `boardmanageId` int(10) NULL DEFAULT NULL COMMENT 'BoardManageID',
  `operatetime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'operatetime',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'table_BoardOrder' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boardorder
-- ----------------------------
INSERT INTO `boardorder` VALUES (1, '18899990000', '2023-09-02 10:52:01', '4', 1, 1, NULL);
INSERT INTO `boardorder` VALUES (5, '18833334444', '2023-09-15 11:39:06', '4', 3, 1, '2023-09-02 11:39:14');

-- ----------------------------
-- Table structure for gly
-- ----------------------------
DROP TABLE IF EXISTS `gly`;
CREATE TABLE `gly`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `yhm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'name',
  `mm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'password',
  `tx` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'avator',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'sex',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'phone',
  `role` int(10) NOT NULL DEFAULT 1 COMMENT 'role',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'table_admin' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gly
-- ----------------------------
INSERT INTO `gly` VALUES (1, 'admin', '123456', 'http://localhost:8080/files/1693622920810-柴犬.jpeg', 'male', '18899990000', 1);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(10) NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'name',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'type',
  `pId` int(10) NULL DEFAULT NULL COMMENT '父ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'table_permission' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '管理员', '2', 2000);
INSERT INTO `permission` VALUES (2, 'user', '2', 2000);
INSERT INTO `permission` VALUES (3, 'BoardManage', '2', 3000);
INSERT INTO `permission` VALUES (4, 'BoardOrder', '2', 3000);
INSERT INTO `permission` VALUES (101, '新增', '3', 1);
INSERT INTO `permission` VALUES (102, '编辑', '3', 1);
INSERT INTO `permission` VALUES (103, '删除', '3', 1);
INSERT INTO `permission` VALUES (104, '查看', '3', 1);
INSERT INTO `permission` VALUES (201, '新增', '3', 2);
INSERT INTO `permission` VALUES (202, '编辑', '3', 2);
INSERT INTO `permission` VALUES (203, '删除', '3', 2);
INSERT INTO `permission` VALUES (204, '查看', '3', 2);
INSERT INTO `permission` VALUES (301, '新增', '3', 3);
INSERT INTO `permission` VALUES (302, '编辑', '3', 3);
INSERT INTO `permission` VALUES (303, '删除', '3', 3);
INSERT INTO `permission` VALUES (304, '查看', '3', 3);
INSERT INTO `permission` VALUES (305, '预约', '3', 3);
INSERT INTO `permission` VALUES (401, '删除', '3', 4);
INSERT INTO `permission` VALUES (402, '查看', '3', 4);
INSERT INTO `permission` VALUES (403, '审核', '3', 4);
INSERT INTO `permission` VALUES (1000, '系统首页', '1', 0);
INSERT INTO `permission` VALUES (1001, '系统首页', '2', 1000);
INSERT INTO `permission` VALUES (2000, '用户管理', '1', 0);
INSERT INTO `permission` VALUES (3000, '信息管理', '1', 0);
INSERT INTO `permission` VALUES (4000, '系统管理', '1', 0);
INSERT INTO `permission` VALUES (4001, '修改密码', '2', 4000);
INSERT INTO `permission` VALUES (4002, '退出登录', '2', 4000);

-- ----------------------------
-- Table structure for role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_rel`;
CREATE TABLE `role_permission_rel`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role` int(10) NULL DEFAULT NULL COMMENT 'role',
  `permissionId` int(10) NULL DEFAULT NULL COMMENT 'permissionID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'role_permission_rel' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_permission_rel
-- ----------------------------
INSERT INTO `role_permission_rel` VALUES (1, 1, 1000);
INSERT INTO `role_permission_rel` VALUES (2, 1, 1001);
INSERT INTO `role_permission_rel` VALUES (3, 1, 2000);
INSERT INTO `role_permission_rel` VALUES (4, 1, 1);
INSERT INTO `role_permission_rel` VALUES (5, 1, 101);
INSERT INTO `role_permission_rel` VALUES (6, 1, 102);
INSERT INTO `role_permission_rel` VALUES (7, 1, 103);
INSERT INTO `role_permission_rel` VALUES (8, 1, 104);
INSERT INTO `role_permission_rel` VALUES (9, 1, 2);
INSERT INTO `role_permission_rel` VALUES (10, 1, 201);
INSERT INTO `role_permission_rel` VALUES (11, 1, 202);
INSERT INTO `role_permission_rel` VALUES (12, 1, 203);
INSERT INTO `role_permission_rel` VALUES (13, 1, 204);
INSERT INTO `role_permission_rel` VALUES (14, 1, 3000);
INSERT INTO `role_permission_rel` VALUES (15, 1, 3);
INSERT INTO `role_permission_rel` VALUES (16, 1, 301);
INSERT INTO `role_permission_rel` VALUES (17, 1, 302);
INSERT INTO `role_permission_rel` VALUES (18, 1, 303);
INSERT INTO `role_permission_rel` VALUES (19, 1, 304);
INSERT INTO `role_permission_rel` VALUES (20, 1, 4);
INSERT INTO `role_permission_rel` VALUES (21, 1, 401);
INSERT INTO `role_permission_rel` VALUES (22, 1, 402);
INSERT INTO `role_permission_rel` VALUES (23, 1, 403);
INSERT INTO `role_permission_rel` VALUES (24, 1, 4000);
INSERT INTO `role_permission_rel` VALUES (25, 1, 4001);
INSERT INTO `role_permission_rel` VALUES (26, 1, 4002);
INSERT INTO `role_permission_rel` VALUES (27, 2, 1000);
INSERT INTO `role_permission_rel` VALUES (28, 2, 1001);
INSERT INTO `role_permission_rel` VALUES (29, 2, 3000);
INSERT INTO `role_permission_rel` VALUES (30, 2, 3);
INSERT INTO `role_permission_rel` VALUES (31, 2, 304);
INSERT INTO `role_permission_rel` VALUES (32, 2, 305);
INSERT INTO `role_permission_rel` VALUES (33, 2, 4);
INSERT INTO `role_permission_rel` VALUES (34, 2, 401);
INSERT INTO `role_permission_rel` VALUES (35, 2, 402);
INSERT INTO `role_permission_rel` VALUES (36, 2, 4000);
INSERT INTO `role_permission_rel` VALUES (37, 2, 4001);
INSERT INTO `role_permission_rel` VALUES (38, 2, 4002);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `yhm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'name',
  `mm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'password',
  `tx` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'avator',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'sex',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'phone',
  `role` int(10) NOT NULL DEFAULT 2 COMMENT 'role',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'table_user' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '123456', 'http://localhost:8080/files/1693622885670-柴犬.jpeg', '男', '18800001122', 2);
INSERT INTO `user` VALUES (3, 'zhangsan', '123456', 'http://localhost:8080/files/1693625450521-柯基.jpeg', 'male', '18833334444', 2);

SET FOREIGN_KEY_CHECKS = 1;
