-- ----------------------------
-- scheme
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `user_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '用户标识',
  `user_name` varchar(64) NOT NULL COMMENT '用户名称',
  `password` varchar(128) DEFAULT NULL COMMENT '用户密码',
  `real_name` varchar(128) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(128) DEFAULT NULL COMMENT '用户电话',
  `mobile` varchar(128) DEFAULT NULL COMMENT '用户手机',
  `type` int(32) DEFAULT NULL COMMENT '用户类型0为系统内置用户无法页面删除',
  `locked` int(32) DEFAULT NULL COMMENT '用户锁定（0正常1锁定）',
  `description` varchar(128) DEFAULT NULL COMMENT '用户描述',
  `status` int(32) DEFAULT NULL COMMENT '用户状态',
  `login_time` bigint(64) DEFAULT NULL COMMENT '用户登录时间',
  `create_time` bigint(64) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(64) DEFAULT NULL COMMENT '修改时间',
  `creator` bigint(64) DEFAULT NULL COMMENT '创建用户',
  `updater` bigint(64) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `role_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '角色标识',
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `description` varchar(128) DEFAULT NULL COMMENT '角色描述',
  `status` int(32) DEFAULT NULL COMMENT '角色状态',
  `create_time` bigint(64) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(64) DEFAULT NULL COMMENT '修改时间',
  `creator` bigint(64) DEFAULT NULL COMMENT '创建用户',
  `updater` bigint(64) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';

DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `user_id` bigint(64) DEFAULT NULL COMMENT '用户标识',
  `role_id` bigint(64) DEFAULT NULL COMMENT '角色标识'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色关系表';

DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission` (
  `role_id` bigint(64) DEFAULT NULL COMMENT '角色标识',
  `permission_code` varchar(64) DEFAULT NULL COMMENT '权限字符串'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色权限关系表';



-- ----------------------------
-- data
-- ----------------------------
-- 系统初始用户
INSERT INTO `t_sys_user` (`user_id`, `user_name`, `password`, `real_name`, `email`, `phone`, `mobile`, `type`, `locked`, `description`, `status`, `login_time`, `create_time`, `update_time`, `creator`, `updater`) VALUES ('1', 'admin', 'd0deb418d4f955326ed2586db9616678d128dac0', '系统管理员', NULL, NULL, NULL, '0', '0', NULL, NULL, NULL, '1503045609036', NULL, NULL, NULL);
-- 系统初始角色
INSERT INTO `t_sys_role` (`role_id`, `role_name`, `description`, `status`, `create_time`, `update_time`, `creator`, `updater`) VALUES ('1', '系统管理员', '系统最高权限的角色', '1', '1503045609063', NULL, NULL, NULL);
-- 系统初始用户角色关联关系
INSERT INTO `t_sys_user_role` (`user_id`, `role_id`) VALUES ('1', '1');




