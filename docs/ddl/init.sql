-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(255) default NULL COMMENT '按钮编码',
  `price` varchar(255) default NULL COMMENT '按钮图标',
  `detail` varchar(255) default NULL COMMENT '按钮名称',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;